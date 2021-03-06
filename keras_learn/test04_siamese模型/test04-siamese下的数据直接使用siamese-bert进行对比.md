## 1 method Two - siamese-bert


- 注意数据的输入形式: 文本都是两两配对
- 注意label是根据batch_size中的文本计算得出

```python
from hydra.utils.gpu_usage import limit_gpu_memory
limit_gpu_memory(int(4) * 1024, 1)
```

## 构建模型

```python
from __future__ import print_function
import json
import os
import numpy as np
from collections import Counter
from bert4keras.backend import keras, K
from bert4keras.layers import Loss
from bert4keras.models import build_transformer_model
from bert4keras.tokenizers import Tokenizer, load_vocab
from bert4keras.optimizers import Adam, extend_with_weight_decay
from bert4keras.snippets import DataGenerator
from bert4keras.snippets import sequence_padding
from bert4keras.snippets import text_segmentate
import pandas as pd

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
```


```python
maxlen = 128
batch_size = 16
# config_path = '/data/bert_model/chinese_L-12_H-768_A-12/bert_config.json'
# checkpoint_path = '/data/bert_model/chinese_L-12_H-768_A-12/bert_model.ckpt'
# dict_path = '/data/bert_model/chinese_L-12_H-768_A-12/vocab.txt'

config_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/bert_config.json'
checkpoint_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/bert_model.ckpt'
dict_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/vocab.txt'
```

```python
# 加载并精简词表，建立分词器
token_dict, keep_tokens = load_vocab(
    dict_path=dict_path,
    simplified=True,
    startswith=['[PAD]', '[UNK]', '[CLS]', '[SEP]'],
)
tokenizer = Tokenizer(token_dict, do_lower_case=True)
```

```python
# 建立加载模型
bert = build_transformer_model(
    config_path,
    checkpoint_path,
    with_pool='linear',
    application='unilm',
    keep_tokens=keep_tokens,  # 只保留keep_tokens中的字，精简原字表
    return_keras_model=True,
    model="bert",
)
```

```python
class TotalLoss(Loss):
    """loss分两部分，一是seq2seq的交叉熵，二是相似度的交叉熵。
    """
    def compute_loss(self, inputs, mask=None):
        loss1 = self.compute_loss_of_seq2seq(inputs, mask)
        loss2 = self.compute_loss_of_similarity(inputs, mask)
        self.add_metric(loss1, name='seq2seq_loss')
        self.add_metric(loss2, name='similarity_loss')
        return loss1 + loss2

    def compute_loss_of_seq2seq(self, inputs, mask=None):
        y_true, y_mask, _, y_pred = inputs
        y_true = y_true[:, 1:]  # 目标token_ids
        y_mask = y_mask[:, 1:]  # segment_ids，刚好指示了要预测的部分
        y_pred = y_pred[:, :-1]  # 预测序列，错开一位
        loss = K.sparse_categorical_crossentropy(y_true, y_pred)
        loss = K.sum(loss * y_mask) / K.sum(y_mask)
        return loss

    def compute_loss_of_similarity(self, inputs, mask=None):
        _, _, y_pred, _ = inputs
        y_true = self.get_labels_of_similarity(y_pred)  # 构建标签
        y_pred = K.l2_normalize(y_pred, axis=1)  # 句向量归一化
        similarities = K.dot(y_pred, K.transpose(y_pred))  # 相似度矩阵
        similarities = similarities - K.eye(K.shape(y_pred)[0]) * 1e12  # 排除对角线
        similarities = similarities * 30  # scale
        loss = K.categorical_crossentropy(
            y_true, similarities, from_logits=True
        )
        return loss

    def get_labels_of_similarity(self, y_pred):
        idxs = K.arange(0, K.shape(y_pred)[0])
        idxs_1 = idxs[None, :]
        idxs_2 = (idxs + 1 - idxs % 2 * 2)[:, None]
        labels = K.equal(idxs_1, idxs_2)
        labels = K.cast(labels, K.floatx())
        return labels
```

```python
encoder = keras.models.Model(bert.inputs, bert.outputs[0])

outputs = TotalLoss([2, 3])(bert.inputs + bert.outputs)

model = keras.models.Model(bert.inputs, outputs)

AdamW = extend_with_weight_decay(Adam, 'AdamW')
optimizer = AdamW(learning_rate=2e-6, weight_decay_rate=0.01)
model.compile(optimizer=optimizer)
```

## 构建数据

```python
with open("./data/siamese/df_train.json", "r", encoding="utf-8") as f:
    train_data = json.load(f)
    
df_test = pd.read_csv("./data/siamese/df_test.txt",sep="\\t", header=None,engine='python')
```

```python
train_data = [i for i in list(train_data.values()) if len(i)>=2]
```

```python
class train_generator(DataGenerator):
    """数据生成器
    """
    def __iter__(self, random=False):
        batch_token_ids, batch_segment_ids, batch_labels = [], [], []
        for is_end, d in self.sample(random):
            text1, text2 = np.random.choice(d,2)
            token_ids, segment_ids = tokenizer.encode(
                text1, text2, maxlen=maxlen
            )
            batch_token_ids.append(token_ids)
            batch_segment_ids.append(segment_ids)
            token_ids, segment_ids = tokenizer.encode(
                text2, text1, maxlen=maxlen
            )
            batch_token_ids.append(token_ids)
            batch_segment_ids.append(segment_ids)
            if len(batch_token_ids) == batch_size or is_end:
                batch_token_ids = sequence_padding(batch_token_ids)
                batch_segment_ids = sequence_padding(batch_segment_ids)
                yield [batch_token_ids, batch_segment_ids], None
                batch_token_ids, batch_segment_ids = [], []
```

```python
train_genrate = train_generator(train_data,batch_size=batch_size)
```

```python
# 测试数据集
test_text = df_test[[0,1]].to_numpy().tolist() 
left_token_ids, left_segment_ids, right_token_ids, rigth_segment_ids = [], [], [],[]
test_label = []
for index, row in df_test.iterrows():
    text1, text2, label = row
    left_token, left_segment = tokenizer.encode(text1, maxlen=maxlen)
    right_token, right_segment = tokenizer.encode(text2, maxlen=maxlen)
    
    left_token_ids.append(left_token)
    left_segment_ids.append(left_segment)
    
    right_token_ids.append(right_token)
    rigth_segment_ids.append(right_segment)
    
    test_label.append([label])

left_token_ids = sequence_padding(left_token_ids)
left_segment_ids = sequence_padding(left_segment_ids)
right_token_ids = sequence_padding(right_token_ids)
rigth_segment_ids = sequence_padding(rigth_segment_ids)
test_label = sequence_padding(test_label)
```

```python
class Evaluate(keras.callbacks.Callback):
    """评估模型
    """
    def __init__(self):
        super().__init__()
        self.best_acc = 0.0

    def on_epoch_end(self, epoch, logs=None):
        pred1 = encoder.predict([left_token_ids, left_segment_ids],verbose=1)
        pred2 = encoder.predict([right_token_ids, rigth_segment_ids],verbose=1)

        dis = np.sum((pred1 * pred2), axis=1)/(np.sum((pred1**2+pred2**2),axis=1)**0.5)

        pred = (dis>0.80).astype("int").reshape(-1,1)
        acc = sum(pred==test_label)/len(pred)
        
        if self.best_acc <= acc:
            self.best_acc = acc
        print("best_acc: {}, acc: {}".format(self.best_acc, acc))
```

```python
steps_per_epoch = int(sum([len(i) for i in train_data])/batch_size) #相当于遍历一遍数据
```

```python
model.fit_generator(train_genrate.forfit(), steps_per_epoch=steps_per_epoch, epochs=3, callbacks=[Evaluate()])
```
