```python
import numpy as np 
import keras
import gensim
import json 
from sklearn.model_selection import train_test_split
from keras.utils import plot_model
import jieba
import tensorflow as tf
```

## 1 构建词向量矩阵 

```python
def get_embedding_matrix(emed_path, embed_size=100):
    embeddings_index = gensim.models.KeyedVectors.load_word2vec_format(
        emed_path, binary=False)
    
    words = embeddings_index.index2word
    embedding_matrix = np.zeros((len(words)+1, embed_size))
    
    for index, word in enumerate(words):
        embedding_matrix[index+1] = embeddings_index[word]
    word_dict = {word: index+1 for index, word in enumerate(words)}
    return embedding_matrix, word_dict
```

```python
emed_path = "model_vec_2.txt"
embedding_matrix, word_dict = get_embedding_matrix(emed_path)
```

```python
# embedding_matrix = np.random.random((135293, 100))
```

```python
embedding_matrix.shape
```

## 2 读取训练数据

```python
train_data_path = "./data/train.txt"
test_data_path =  "./data/test.txt"

max_len = 128  # 每个样本保留128个词的长度
```

```python
## 数据 1
label2index = {"B":0, "G":1}

with open("./data/train.txt") as f:
    x_raw = f.readlines()
x_raw = [json.loads(i) for i in x_raw]

x_tensor, y_tensor = [], []

for line in x_raw:
    x_segs = [word_dict[i] if i in word_dict else 0 for i in line["words"]]
    padding = [0] * (max_len-len(x_segs))
    x_tensor.append(x_segs + padding)
    y_tensor.append([label2index[line["label"]]])
    
x_tensor = np.array(x_tensor)
y_tensor = np.array(y_tensor)
```

```python
# x_tensor
```

```python
# y_tensor
```

## 3 构建模型

```python
inputs = keras.layers.Input(shape=(max_len,))
x = keras.layers.Embedding(embedding_matrix.shape[0],
                           output_dim=embedding_matrix.shape[1],
                           weights=[embedding_matrix],
                           input_length=max_len,
                           trainable=True)(inputs)
flatten = keras.layers.Lambda(lambda x: tf.reduce_sum(x, axis=1))(x)
dense = keras.layers.Dense(len(label2index), activation='softmax')(flatten)
model = keras.models.Model(inputs=[inputs], outputs=[dense])
model.compile(loss=keras.losses.SparseCategoricalCrossentropy(),
              optimizer=keras.optimizers.Adam(), 
              metrics=[keras.metrics.SparseCategoricalAccuracy(name='train_accuracy')])
model.summary()
```

```python
plot_model(model,to_file='model.png',show_shapes=True)
```

## 4 训练模型

```python
model.compile(loss ="sparse_categorical_crossentropy", optimizer = "adam", metrics=["acc"]) 
model.fit(x=x_tensor,y=y_tensor,epochs=20, validation_split=0.2, batch_size=128)
```

## 5 模型预测

```python
test = ["衣服不好，质量很差","衣服不错，下次还来"]
x_pred = []
for i in test:
    x_segs = [word_dict[i] if i in word_dict else 0 for i in jieba.cut(i)]
    padding = [0] * (max_len-len(x_segs))
    x_pred.append(x_segs + padding)
y_pred = model.predict(x_pred)
for i, j in zip(test,y_pred):
    print(i, j)
```

```python

```
