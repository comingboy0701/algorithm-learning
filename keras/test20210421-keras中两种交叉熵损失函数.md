## 1 binary_cross_entropy

- 用于二分类损失，使用sigmoid激活函数


```python
import tensorflow as tf
import numpy as np
import keras.backend as K
import keras
```

```python
def sigmoid(x):
    return 1.0/(1+np.exp(-x))

y_true = np.array([1,0,0,1]).astype(np.float64)
print("y_true=\n{}".format(y_true))

logits = np.array([12,3,1,-1]).astype(np.float64)
print("logits=\n{}".format(logits))

y_pred = sigmoid(logits)
print("y_pred=\n{}".format(y_pred))

BCE1 = -y_true*np.log(y_pred)-(1-y_true)*np.log(1-y_pred)
print("BCE1=\n{}".format(BCE1))

sess =tf.Session()
BCE2 = sess.run(tf.nn.sigmoid_cross_entropy_with_logits(labels=y_true,logits=logits))
print("BCE2=\n{}".format(BCE2))
```

    y_true=
    [1. 0. 0. 1.]
    logits=
    [12.  3.  1. -1.]
    
    y_pred=
    [0.99999386 0.95257413 0.73105858 0.26894142]
    
    BCE1=
    [6.14419348e-06 3.04858735e+00 1.31326169e+00 1.31326169e+00]

    BCE2=
    [6.14419348e-06 3.04858735e+00 1.31326169e+00 1.31326169e+00]


- 使用keras实现-未使用激活函数的logits


```python
y_true_input = keras.layers.Input(shape=[1, ])
logits_input = keras.layers.Input(shape=[1, ])
loss_output = keras.layers.Lambda(
    lambda x:K.binary_crossentropy(x[0], x[1], from_logits=True)
)([y_true_input, logits_input])
model = keras.models.Model(inputs=[y_true_input, logits_input], outputs=loss_output)
BCE3 = model.predict([y_true, logits])
print("BCE3=\n{}".format(BCE3))
```

    BCE3=
    [[6.1441933e-06]
     [3.0485873e+00]
     [1.3132617e+00]
     [1.3132617e+00]]


- 使用keras实现-使用激活函数后的pred

```python
y_true_input = keras.layers.Input(shape=[1, ])
y_pred_input = keras.layers.Input(shape=[1, ])
loss_output = keras.layers.Lambda(
    lambda x:K.binary_crossentropy(x[0], x[1], from_logits=False)
)([y_true_input, y_pred_input])
model = keras.models.Model(inputs=[y_true_input, y_pred_input], outputs=loss_output)
BCE3 = model.predict([y_true, y_pred])
print("BCE3=\n{}".format(BCE3))
```

    BCE3=
    [[6.0200873e-06]
     [3.0485854e+00]
     [1.3132614e+00]
     [1.3132613e+00]]


## 2 categorical_cross_entropy

- 用于多分类损失，使用softmax激活函数


```python
y_true = np.array([[1,0,0],[0,1,0],[0,1,0],[0,1,0],[0,1,0]])
logits = np.array([[12,3,2],[3,10,1],[1,2,5],[4,6.5,1.2],[3,6,1]])

def softmax(x):
    sum_raw = np.sum(np.exp(x),axis=-1)
    x1 = np.ones(np.shape(x))
    for i in range(np.shape(x)[0]):
        x1[i] = np.exp(x[i])/sum_raw[i]
    return x1

y_pred = softmax(logits)

CE1 = -np.sum(y_true*np.log(y_pred),-1)

CE2 = sess.run(tf.nn.softmax_cross_entropy_with_logits_v2(labels=y_true,logits=logits))

print(CE1)
print(CE2)
```

    [1.68795487e-04 1.03475622e-03 3.06588390e+00 8.34920680e-02
     5.49852354e-02]
    [1.68795487e-04 1.03475622e-03 3.06588390e+00 8.34920680e-02
     5.49852354e-02]


- 使用keras实现-未使用激活函数的logits


```python
y_true_input = keras.layers.Input(shape=[3, ])
logits_input = keras.layers.Input(shape=[3, ])
loss_output = keras.layers.Lambda(
    lambda x:K.categorical_crossentropy(x[0], x[1], from_logits=True)
)([y_true_input, logits_input])
model = keras.models.Model(inputs=[y_true_input, logits_input], outputs=loss_output)
BCE3 = model.predict([y_true, logits])
print("BCE3=\n{}".format(BCE3))
```

    BCE3=
    [1.6878611e-04 1.0346780e-03 3.0658839e+00 8.3492130e-02 5.4985214e-02]


- 使用keras实现-使用激活函数后的pred


```python
y_true_input = keras.layers.Input(shape=[3, ])
logits_input = keras.layers.Input(shape=[3, ])
loss_output = keras.layers.Lambda(
    lambda x:K.categorical_crossentropy(x[0], x[1], from_logits=False)
)([y_true_input, logits_input])
model = keras.models.Model(inputs=[y_true_input, logits_input], outputs=loss_output)
BCE3 = model.predict([y_true, y_pred])
print("BCE3=\n{}".format(BCE3))
```

    BCE3=
    [1.6881460e-04 1.0347354e-03 3.0658839e+00 8.3492063e-02 5.4985248e-02]


