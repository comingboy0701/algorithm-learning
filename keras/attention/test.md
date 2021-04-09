```python
import tensorflow as tf
import numpy as np
import keras.backend as K
from keras.layers import Dense, Lambda, Input, Add

```

```python
def rel_position_id(x):
    x1, x2 = x
    x1 = K.expand_dims(x1, 2)
    x2 = K.expand_dims(x2, 1)
    return x1 - x2

def Pos_Embedding(inputs, maxlen=512):
    indices = K.arange(0, 512, dtype=K.floatx())
    indices = K.pow(10000.0, 1 * indices / 512.0)
    
    inputs = inputs/indices
    sin = K.sin(inputs) * tf.cast([1 if i % 2 == 0 else 0 for i in range(maxlen)], dtype=tf.float32)
    cos = K.cos(inputs) * tf.cast([1 if i % 2 == 1 else 0 for i in range(maxlen)], dtype=tf.float32)
    return sin+cos


p1 = Input(shape=(512,))
p2 = Input(shape=(512,))

p11 = Lambda(rel_position_id)([p1, p1])
p12 = Lambda(rel_position_id)([p1, p2])
p21 = Lambda(rel_position_id)([p2, p1])
p22 = Lambda(rel_position_id)([p2, p2])

pv_1 = Lambda(Pos_Embedding)(p11)
pv_2 = Lambda(Pos_Embedding)(p12)
pv_3 = Lambda(Pos_Embedding)(p21)
pv_4 = Lambda(Pos_Embedding)(p22)

alpha = Add()([pv_1, pv_4])
beta = Add()([pv_2, pv_3])
alpha = Dense(128, activation='relu', use_bias=False)(alpha)
beta = Dense(128, activation='relu', use_bias=False)(beta)
```

```python
beta
```
