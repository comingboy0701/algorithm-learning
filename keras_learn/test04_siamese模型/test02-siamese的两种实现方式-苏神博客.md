## 1 method Two


- 注意数据的输入形式: 文本都是两两配对
- 注意label是根据batch_size中的文本计算得出

```python
from hydra.utils.gpu_usage import limit_gpu_memory
limit_gpu_memory(int(2) * 1024, 1)
```

## 构建数据

```python
import pandas as pd
import numpy as np
import gensim
import json
import random
from keras.preprocessing.text import Tokenizer, text_to_word_sequence
from keras.preprocessing.sequence import pad_sequences
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
```

```python
batch_size  = 512
```

```python
with open("./data/siamese/df_train.json", "r", encoding="utf-8") as f:
    train_data = json.load(f)
    
df_test = pd.read_csv("./data/siamese/df_test.txt",sep="\\t", header=None,engine='python')
```

```python
train_text = list(train_data.values())
test_text = df_test[[0,1]].values.tolist()
```

```python
tokenizer_text = [ list(j) for i in train_text + test_text for j in i]
```

```python
tokenizer = Tokenizer(num_words=None,lower=False, char_level=True, split="")
tokenizer.fit_on_texts(tokenizer_text)
```

```python
word_dict = tokenizer.word_index
word_num = len(word_dict)+1
max_length = 128 #max(len(i) for i in tokenizer_text)
```

```python
word_num, max_length
```

```python
train_data = [i for i in train_text if len(i)>=2]
```

```python
#数据生成器
def generator(data, shuffle=True, batch_size=128, maxlen = 128):
    min_index, max_index = 0, len(data)
    i  = min_index
    while True:
        if shuffle:
            random.shuffle(data)
        if i + batch_size >= max_index:
            i = min_index #超过记录序号时，从头开始
        rows = np.arange(i, min(i+batch_size, max_index))
        i += len(rows)
        train_text1 = []
        for index in rows:
            text1, text2 = random.choices(data[index],k=2)
            train_text1.append(list(text1)+list(text2))
            train_text1.append(list(text2)+list(text1))
        x1 = tokenizer.texts_to_sequences(train_text1)
        x1 = pad_sequences(x1, maxlen=maxlen, value=0,truncating='post',padding='post')
        yield x1, None
```

```python
train_genrate = generator(train_data,batch_size=4)
```

```python
sent1 = [ list(i) for i in df_test[0].tolist()]
sent2 = [ list(i) for i in df_test[1].tolist()]

x1 = tokenizer.texts_to_sequences(sent1)
x2 = tokenizer.texts_to_sequences(sent2)

x1 = pad_sequences(x1, maxlen=max_length, value=0,truncating='post',padding='post')
x2 = pad_sequences(x2, maxlen=max_length, value=0,truncating='post',padding='post')
y = df_test[[2]].to_numpy()
```

### 加载词向量

```python
emb_path = "./data/siamese/token_vec_300.bin"
word_dim = 300
embedding_dict = gensim.models.KeyedVectors.load_word2vec_format(emb_path, binary=False)

embedding_matrix = np.zeros((word_num, word_dim))
for word, i in word_dict.items():
    if word in embedding_dict.vocab:
        embedding_matrix[i] = embedding_dict[word]
```

## 构建模型

```python
import keras
from keras.layers import Layer
from keras.utils import plot_model
from keras import backend as K
from keras.preprocessing.sequence import pad_sequences
from keras.models import Model, load_model
from keras.layers import Input, Embedding, LSTM, Dropout, Lambda, Bidirectional
import numpy as np

input1 = Input(shape=(max_length,))
input2 = Input(shape=(max_length,))
input3 = Input(shape=(max_length,))

embedding_layer = Embedding(input_dim = word_num,
                            output_dim = 300,
                            weights=[embedding_matrix],
                            input_length=max_length,
                            trainable= False,
                            mask_zero=True)

emb_input = embedding_layer(input1)

lstm1 = Bidirectional(LSTM(128, return_sequences=True))(emb_input)
lstm1 = Dropout(0.5)(lstm1)
lstm2 = Bidirectional(LSTM(32))(lstm1)
lstm2 = Dropout(0.5)(lstm2) 
```

```python
class Loss(Layer):
    """特殊的层，用来定义复杂loss
    """
    def __init__(self, **kwargs):
        super(Loss, self).__init__(**kwargs)

    def call(self, inputs, mask=None):
        loss = self.compute_loss(inputs, mask)
        self.add_loss(loss)
        return inputs

    def compute_loss(self, inputs, mask=None):
        loss1 = self.compute_loss_of_similarity(inputs,)
        self.add_metric(loss1, name='similarity_loss')
        return loss1
        
    def compute_loss_of_similarity(self, y_pred,mask=None):
        y_true = self.get_labels_of_similarity(y_pred)  # 构建标签
        y_pred = K.l2_normalize(y_pred, axis=1)  # 句向量归一化
        similarities = K.dot(y_pred, K.transpose(y_pred))  # 相似度矩阵
        similarities = similarities - K.eye(K.shape(y_pred)[0]) * 1e12  # 排除对角线e
        similarities = similarities * 30  # scale
        loss = K.categorical_crossentropy(
            y_true, similarities, from_logits=True
        )
        return loss

    def get_labels_of_similarity(self, y_pred, mask=None):
        idxs = K.arange(0, K.shape(y_pred)[0])
        idxs_1 = idxs[None, :]
        idxs_2 = (idxs + 1 - idxs % 2 * 2)[:, None]
        labels = K.equal(idxs_1, idxs_2)
        labels = K.cast(labels, K.floatx())
        return labels 
```

```python
output = Loss(name="output")(lstm2)
model = Model(inputs=input1, outputs=output)
model.compile(optimizer='adam')
```

```python
from keras.utils import plot_model
```

```python
# plot_model(model,to_file='siamese_model2.png', show_shapes=True)
```

```python
# ![](./siamese_model2.png) 
```

```python
test_sent1 = [list(i) for i in df_test[0].tolist()]
test_x1 = tokenizer.texts_to_sequences(test_sent1)
test_x1 = pad_sequences(test_x1, maxlen=max_length, value=0,truncating='post',padding='post')

test_sent2 = [list(i) for i in df_test[1].tolist()]
test_x2 = tokenizer.texts_to_sequences(test_sent2)
test_x2 = pad_sequences(test_x2, maxlen=max_length, value=0,truncating='post',padding='post')                  
                   
label = df_test[2].tolist()
```

```python
class Evaluate(keras.callbacks.Callback):
    """评估模型
    """
    def __init__(self):
        super().__init__()
        self.best_acc = 0.0

    def on_epoch_end(self, epoch, logs=None):
        pred1 = model.predict([x1],verbose=1)
        pred2 = model.predict([x2],verbose=1)

        dis = np.sum((pred1 * pred2), axis=1)/(np.sum((pred1**2+pred2**2),axis=1)**0.5)

        pred = (dis>0.80).astype("int").reshape(-1,1)
        acc = sum(pred==y)/len(pred)

        if self.best_acc <= acc:
            self.best_acc = acc
        print("best_acc: {}, acc: {}".format(self.best_acc, acc))
```

```python
evaluate = Evaluate()
```

```python
steps_per_epoch = int(sum([ len(i) for i in train_text])/batch_size) #相当于遍历一遍数据
```

```python
model.fit_generator(train_genrate, steps_per_epoch=steps_per_epoch, epochs=20,callbacks=[evaluate])
```

```python
'''

Epoch 1/20
202/202 [==============================] - 64s 318ms/step - loss: 2.4567 - similarity_loss: 2.4567
20000/20000 [==============================] - 40s 2ms/step
20000/20000 [==============================] - 40s 2ms/step
best_acc: [0.66275], acc: [0.66275]
Epoch 2/20
202/202 [==============================] - 63s 309ms/step - loss: 1.9602 - similarity_loss: 1.9602
20000/20000 [==============================] - 40s 2ms/step
20000/20000 [==============================] - 40s 2ms/step
best_acc: [0.66415], acc: [0.66415]
Epoch 3/20
202/202 [==============================] - 63s 314ms/step - loss: 1.8104 - similarity_loss: 1.8104
20000/20000 [==============================] - 37s 2ms/step
20000/20000 [==============================] - 39s 2ms/step
best_acc: [0.673], acc: [0.673]
Epoch 4/20
202/202 [==============================] - 59s 291ms/step - loss: 1.6254 - similarity_loss: 1.6254
20000/20000 [==============================] - 38s 2ms/step
20000/20000 [==============================] - 38s 2ms/step
best_acc: [0.67425], acc: [0.67425]
Epoch 5/20
202/202 [==============================] - 61s 302ms/step - loss: 1.7714 - similarity_loss: 1.7714
20000/20000 [==============================] - 40s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.67545]
Epoch 6/20
202/202 [==============================] - 62s 309ms/step - loss: 1.4916 - similarity_loss: 1.4916
20000/20000 [==============================] - 41s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.673]
Epoch 7/20
202/202 [==============================] - 62s 309ms/step - loss: 1.4082 - similarity_loss: 1.4082
20000/20000 [==============================] - 41s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.66565]
Epoch 8/20
202/202 [==============================] - 63s 313ms/step - loss: 1.3708 - similarity_loss: 1.3708
20000/20000 [==============================] - 42s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.66685]
Epoch 9/20
202/202 [==============================] - 62s 309ms/step - loss: 1.3681 - similarity_loss: 1.3681
20000/20000 [==============================] - 41s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.6675]
Epoch 10/20
202/202 [==============================] - 63s 310ms/step - loss: 1.3425 - similarity_loss: 1.3425
20000/20000 [==============================] - 41s 2ms/step
20000/20000 [==============================] - 41s 2ms/step
best_acc: [0.67545], acc: [0.666]
Epoch 11/20
202/202 [==============================] - 63s 309ms/step - loss: 1.2651 - similarity_loss: 1.2651
17504/20000 [=========================>....] - ETA: 5s

'''
```
