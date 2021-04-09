## 1 method Two -bert

```python
import numpy as np
from bert4keras.backend import keras, set_gelu, K
from bert4keras.tokenizers import Tokenizer
from bert4keras.models import build_transformer_model
from bert4keras.optimizers import Adam
from bert4keras.snippets import sequence_padding, DataGenerator
from bert4keras.snippets import open
from keras.layers import Dropout, Dense
import pandas as pd
import tensorflow as tf
import os

set_gelu('tanh')  # 切换gelu版本set_visible_devices
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
```


```python
from hydra.utils.gpu_usage import limit_gpu_memory
limit_gpu_memory(int(8) * 1024, 1)
```

```python
maxlen = 128
batch_size = 16

config_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/bert_config.json'
checkpoint_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/bert_model.ckpt'
dict_path = '/data/bert_model/roberta_wwm_ext_L-3_H-768_A-12/vocab.txt'
```

```python
# 建立分词器
tokenizer = Tokenizer(dict_path, do_lower_case=True)
```

```python
# 加载数据集
df_train = pd.read_csv("./data/siamese/df_train.txt",sep="\\t", header=None,engine='python')
df_test = pd.read_csv("./data/siamese/df_test.txt",sep="\\t", header=None,engine='python')
```

```python
train_data =  df_train.values.tolist()
test_data =  df_test.values.tolist()
```

```python
class data_generator(DataGenerator):
    """数据生成器
    """
    def __iter__(self, random=False):
        batch_token_ids, batch_segment_ids, batch_labels = [], [], []
        for is_end, (text1, text2, label) in self.sample(random):
            token_ids, segment_ids = tokenizer.encode(
                text1, text2, maxlen=maxlen
            )
            batch_token_ids.append(token_ids)
            batch_segment_ids.append(segment_ids)
            batch_labels.append([label])
            if len(batch_token_ids) == self.batch_size or is_end:
                batch_token_ids = sequence_padding(batch_token_ids)
                batch_segment_ids = sequence_padding(batch_segment_ids)
                batch_labels = sequence_padding(batch_labels)
                yield [batch_token_ids, batch_segment_ids], batch_labels
                batch_token_ids, batch_segment_ids, batch_labels = [], [], []
```

```python
# 加载预训练模型
bert = build_transformer_model(
    config_path=config_path,
    checkpoint_path=checkpoint_path,
    with_pool=True,
    return_keras_model=False,
)
```

```python
output = Dropout(rate=0.1)(bert.model.output)
output = Dense(
    units=2, activation='softmax', kernel_initializer=bert.initializer
)(output)
```

```python
model = keras.models.Model(bert.model.input, output)
# model.summary()
```

```python
model.compile(
    loss='sparse_categorical_crossentropy',
    optimizer=Adam(2e-5),  # 用足够小的学习率
    metrics=['accuracy'],
)
```

```python
# 转换数据集
train_generator = data_generator(train_data, batch_size)
test_generator = data_generator(test_data, batch_size)
```

```python
model.fit_generator(
    train_generator.forfit(),
    steps_per_epoch=len(train_generator),
    epochs=20,
    validation_data = test_generator.forfit(),
    validation_steps = len(test_generator)//batch_size)
```

Epoch 1/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.4105 - accuracy: 0.8114 - val_loss: 0.1294 - val_accuracy: 0.8774

Epoch 2/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.2635 - accuracy: 0.8924 - val_loss: 0.5056 - val_accuracy: 0.8974

Epoch 3/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.1780 - accuracy: 0.9302 - val_loss: 0.5339 - val_accuracy: 0.9022

Epoch 4/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.1253 - accuracy: 0.9520 - val_loss: 0.5701 - val_accuracy: 0.9046

Epoch 5/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.0915 - accuracy: 0.9659 - val_loss: 0.1144 - val_accuracy: 0.9239

Epoch 6/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.0704 - accuracy: 0.9742 - val_loss: 0.3193 - val_accuracy: 0.9030

Epoch 7/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.0572 - accuracy: 0.9791 - val_loss: 0.0916 - val_accuracy: 0.9175

Epoch 8/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0477 - accuracy: 0.9828 - val_loss: 0.3937 - val_accuracy: 0.9071

Epoch 9/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0404 - accuracy: 0.9858 - val_loss: 3.5558e-04 - val_accuracy: 0.9191

Epoch 10/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0358 - accuracy: 0.9871 - val_loss: 0.0038 - val_accuracy: 0.9439

Epoch 11/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0313 - accuracy: 0.9887 - val_loss: 0.8998 - val_accuracy: 0.9175

Epoch 12/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0289 - accuracy: 0.9896 - val_loss: 0.0067 - val_accuracy: 0.9215

Epoch 13/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0253 - accuracy: 0.9915 - val_loss: 0.0010 - val_accuracy: 0.9319

Epoch 14/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0247 - accuracy: 0.9915 - val_loss: 0.0031 - val_accuracy: 0.9383

Epoch 15/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0219 - accuracy: 0.9923 - val_loss: 1.1406 - val_accuracy: 0.9239

Epoch 16/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0203 - accuracy: 0.9932 - val_loss: 0.0211 - val_accuracy: 0.9247

Epoch 17/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0201 - accuracy: 0.9933 - val_loss: 0.8890 - val_accuracy: 0.9223

Epoch 18/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0198 - accuracy: 0.9931 - val_loss: 0.7612 - val_accuracy: 0.9279

Epoch 19/20
5000/5000 [==============================] - 146s 29ms/step - loss: 0.0164 - accuracy: 0.9944 - val_loss: 1.2139 - val_accuracy: 0.9327

Epoch 20/20
5000/5000 [==============================] - 145s 29ms/step - loss: 0.0178 - accuracy: 0.9941 - val_loss: 0.6815 - val_accuracy: 0.9335
