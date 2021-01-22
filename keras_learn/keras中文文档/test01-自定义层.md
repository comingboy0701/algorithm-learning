```python
from tensorflow.keras import models
from keras.utils import plot_model
```

```python
from keras import backend as K
from keras.engine.topology import Layer

class MyLayer(Layer):

    def __init__(self, output_dim, **kwargs):
        self.output_dim = output_dim
        super(MyLayer, self).__init__(**kwargs)

    def build(self, input_shape):
        # 为该层创建一个可训练的权重
        self.kernel = self.add_weight(name='kernel', 
                                      shape=(input_shape[1], self.output_dim),
                                      initializer='uniform',
                                      trainable=True)
        self.bias = self.add_weight(name='kernel', 
                                      shape=(self.output_dim,),
                                      initializer='uniform',
                                      trainable=True)
        super(MyLayer, self).build(input_shape)  # 一定要在最后调用它

    def call(self, x):
        return K.dot(x, self.kernel)+self.bias

    def compute_output_shape(self, input_shape):
        return (input_shape[0], self.output_dim)
    
    def get_config(self):
        config = {
            'output_dim': self.output_dim
        }
        base_config = super(MyLayer, self).get_config()
        return dict(list(base_config.items()) + list(config.items()))
```

```python
inputs = layers.Input(shape=[128])
x  = layers.Embedding(10000,256)(inputs)
x2 = layers.Flatten()(x)
```

```python
x3 = MyLayer(64)(x2)
```

```python
model = models.Model(inputs = inputs,outputs = x3)
```

```python
plot_model(model,to_file='model.png',show_shapes=True)
```

```python
model.summary()
```
