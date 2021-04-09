## 一、交叉熵

```python
import numpy as np

import matplotlib.pyplot as plt
```

```python
x = np.linspace(0,1,50)
y = np.log(x)
plt.plot(x,y)
```

交叉熵在单分类问题中的应用
在线性回归问题中，常常使用MSE(Mean Squared Error)作为loss函数，而在分类问题中常常使用交叉熵作为loss函数。

下面通过一个例子来说明如何计算交叉熵损失值。

假设我们输入一张狗的图片，标签与预测值如下：

|类别|猫|狗|马|
| --- | --- | --- | --- | 
|label|0|1|0|
|Pred|0.2|0.7|0.1|

loss=−(0∗log(0.2)+1∗log(0.7)+0∗log(0.1))=0.36



## 二、输入输出设计

```python
from keras.layers import Input,Embedding,LSTM,Dense
from keras.models import Model
from keras import backend as K
from keras.utils import plot_model

word_size = 128
nb_features = 10000
nb_classes = 10
encode_size = 64

input = Input(shape=(None,))
embedded = Embedding(nb_features,word_size)(input)
encoder = LSTM(encode_size)(embedded)
predict = Dense(nb_classes, activation='softmax')(encoder)

def mycrossentropy(y_true, y_pred, e=0.1):
    loss1 = K.categorical_crossentropy(y_true, y_pred)
    loss2 = K.categorical_crossentropy(K.ones_like(y_pred)/nb_classes, y_pred)
    return (1-e)*loss1 + e*loss2

model = Model(inputs=input, outputs=predict)
model.compile(optimizer='adam', loss=mycrossentropy)
```

```python
plot_model(model,to_file='model.png',show_shapes=True)
```

## 三、并不仅仅是输入输出那么简单 #

```python
from keras.layers import Input,Embedding,LSTM,Dense,Lambda
from keras.layers.merge import dot
from keras.models import Model
from keras import backend as K

word_size = 128
nb_features = 10000
nb_classes = 10
encode_size = 64
margin = 0.1

embedding = Embedding(nb_features,word_size)
lstm_encoder = LSTM(encode_size)

def encode(input):
    return lstm_encoder(embedding(input))

q_input = Input(shape=(None,))
a_right = Input(shape=(None,))
a_wrong = Input(shape=(None,))
q_encoded = encode(q_input)
a_right_encoded = encode(a_right)
a_wrong_encoded = encode(a_wrong)

q_encoded = Dense(encode_size)(q_encoded) #一般的做法是，直接讲问题和答案用同样的方法encode成向量后直接匹配，但我认为这是不合理的，我认为至少经过某个变换。

right_cos = dot([q_encoded,a_right_encoded], -1, normalize=True)
wrong_cos = dot([q_encoded,a_wrong_encoded], -1, normalize=True)

loss = Lambda(lambda x: K.relu(margin+x[0]-x[1]))([wrong_cos,right_cos])
```

```python
loss
```

```python
model_train = Model(inputs=[q_input,a_right,a_wrong], outputs=loss)
model_q_encoder = Model(inputs=q_input, outputs=q_encoded)
model_a_encoder = Model(inputs=a_right, outputs=a_right_encoded)

model_train.compile(optimizer='adam', loss=lambda y_true,y_pred: y_pred)
model_q_encoder.compile(optimizer='adam', loss='mse')
model_a_encoder.compile(optimizer='adam', loss='mse')

# model_train.fit([q,a1,a2], y, epochs=10)
#其中q,a1,a2分别是问题、正确答案、错误答案的batch，y是任意形状为(len(q),1)的矩阵
```

```python
plot_model(model_train,to_file='model.png',show_shapes=True)
```

```python

```

```python

```
