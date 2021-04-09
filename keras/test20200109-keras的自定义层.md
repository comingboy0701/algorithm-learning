## 1 层的自定义


### 1.1 通过lambda 简单的实现- 不需要加入额外的训练参数

```python
import keras
import numpy as np

x_in = keras.layers.Input(shape=(128,))

x_out = keras.layers.Lambda(lambda x:x+2)(x_in) # 对输入加上2

model = keras.models.Model(x_in, x_out)

x = np.random.normal(size=(10,128))
y = model(x)

print(x[0,:10])
print(y[0,:10])
```

[ 1.48273968  0.63479084  0.11992579 -1.36419116 -0.4741768   0.45395773
  0.0443875   0.73290861  1.14851456 -0.04642981]
  
tf.Tensor(
[3.4827397 2.634791  2.1199257 0.6358088 1.5258232 2.4539578 2.0443876
 2.7329087 3.1485145 1.9535701], shape=(10,), dtype=float32)

```python
from keras import backend as K

def add_noise_in_train(x):
    x_ = x + K.random_normal(shape=K.shape(x)) # 加上标准高斯噪声
    return K.in_train_phase(x_, x)

x_in = keras.layers.Input(shape=(128,))
x_out = keras.layers.Lambda(add_noise_in_train)(x_in) # 训练阶段加入高斯噪声，测试阶段去掉
```

```python
model = keras.models.Model(x_in, x_out)

x = np.random.normal(size=(16,128))
y1 = model(x) # 训练时计算的 输出

print(x[0,:10])
print(y1[0,:10])
```

[-0.37020795  1.48401947 -0.09451537 -0.19106513 -0.13521896 -1.06938527
 -0.14305329  2.4191247  -2.52569469  1.95977302]
 
tf.Tensor(
[-0.37020794  1.4840195  -0.09451537 -0.19106513 -0.13521895 -1.0693853
 -0.1430533   2.4191246  -2.5256946   1.9597731 ], shape=(10,), dtype=float32)


### 1.2 自定义层加入训练的参数

```python
class MyLayer(keras.layers.Layer):

    def __init__(self, output_dim, **kwargs):
        self.output_dim = output_dim # 可以自定义一些属性，方便调用
        super(MyLayer, self).__init__(**kwargs) # 必须

    def build(self, input_shape):
        # 添加可训练参数
        self.kernel = self.add_weight(name='kernel', 
                                      shape=(input_shape[1], self.output_dim),
                                      initializer='uniform',
                                      trainable=True)
        self.bias = self.add_weight(name='bias', 
                                      shape=(self.output_dim,),
                                      initializer='uniform',
                                      trainable=True)
    def call(self, x):
        # 定义功能，相当于Lambda层的功能函数
        return K.dot(x, self.kernel)+self.bias

    def compute_output_shape(self, input_shape):
        # 计算输出形状，如果输入和输出形状一致，那么可以省略，否则最好加上
        return (input_shape[0], self.output_dim)
```

```python
x_in = keras.layers.Input(shape=(128,))
mylayer = MyLayer(2)
x_out = mylayer(x_in)
model = keras.models.Model(x_in, x_out)
```

```python
model.summary()
```

```python
x = np.random.normal(size=(16,128))
y1 = model(x) # 训练时计算的 输出

print(x[0,:10])
print(y1[0,:10])
```

```python
class SplitVector(keras.layers.Layer):

    def __init__(self, **kwargs):
        super(SplitVector, self).__init__(**kwargs)

    def call(self, inputs):
        # 按第二个维度对tensor进行切片，返回一个list
        in_dim = K.int_shape(inputs)[-1]
        print(in_dim)
        return [inputs[:, :in_dim//2], inputs[:, in_dim//2:]]

    def compute_output_shape(self, input_shape):
        # output_shape也要是对应的list
        in_dim = input_shape[-1]
        return [(None, in_dim//2), (None, in_dim-in_dim//2)]
```

```python
x_in = keras.layers.Input(shape=(128))
plit_vector = SplitVector()
x_out = plit_vector(x_in)
model = keras.models.Model(x_in, x_out)
```

```python
model.summary()
```

```python
x = np.random.normal(size=(16,128))
y1, y2 = model(x) # 训练时计算的 输出

print(x[0,:10])
print(y1[0,:10])
print(y2[0,:10])
```

### 1.3 层与loss的结合

```python
class Dense_with_Center_loss(keras.layers.Layer):

    def __init__(self, output_dim, **kwargs):
        self.output_dim = output_dim
        super(Dense_with_Center_loss, self).__init__(**kwargs)

    def build(self, input_shape):
        # 添加可训练参数
        self.kernel = self.add_weight(name='kernel',
                                      shape=(input_shape[1], self.output_dim),
                                      initializer='glorot_normal',
                                      trainable=True)
        self.bias = self.add_weight(name='bias',
                                    shape=(self.output_dim,),
                                    initializer='zeros',
                                    trainable=True)
        self.centers = self.add_weight(name='centers',
                                       shape=(self.output_dim, input_shape[1]),
                                       initializer='glorot_normal',
                                       trainable=True)

    def call(self, inputs):
        # 对于center loss来说，返回结果还是跟Dense的返回结果一致
        # 所以还是普通的矩阵乘法加上偏置
        self.inputs = inputs
        return K.dot(inputs, self.kernel) + self.bias

    def compute_output_shape(self, input_shape):
        return (input_shape[0], self.output_dim)

    def loss(self, y_true, y_pred, lamb=0.5):
        # 定义完整的loss
        y_true = K.cast(y_true, 'int32') # 保证y_true的dtype为int32
        crossentropy = K.sparse_categorical_crossentropy(y_true, y_pred, from_logits=True)
        centers = K.gather(self.centers, y_true[:, 0]) # 取出样本中心
        center_loss = K.sum(K.square(centers - self.inputs), axis=1) # 计算center loss
        return crossentropy + lamb * center_loss

```

```python
f_size = 128

x_in = keras.layers.Input(shape=(784,))
f = keras.layers.Dense(f_size)(x_in)

dense_center = Dense_with_Center_loss(10)
output = dense_center(f)

model = keras.models.Model(x_in, output)
model.compile(loss=dense_center.loss,
              optimizer='adam',
              metrics=['sparse_categorical_accuracy'])

# 这里是y_train是类别的整数id，不用转为one hot
# model.fit(x_train, y_train, epochs=10)
```

```python
model.summary()
```

```python

```
