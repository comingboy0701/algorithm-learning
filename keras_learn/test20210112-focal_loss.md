### focal loss

```python
import numpy as np
import keras
import keras.backend as K
import tensorflow as tf
```

```python
label_num_list = [100,200,50,10] # 假设分类的label有4类，每类的样本数分贝是 100,200,50,10

total_sample_num = float(sum(label_num_list))

def multi_category_focal_loss(y_true, y_pred):
    gamma = tf.cast(2.0,tf.float64)
    epsilon = tf.cast(1.e-7,tf.float64)
    classes_weight = [total_sample_num / label_num for label_num in label_num_list]
    classes_weight_norm = [weight / sum(classes_weight) for weight in classes_weight]  # 按类别得到权重进行归一化
    classes_weight_norm = tf.cast(classes_weight_norm, tf.float64) 
    alpha = tf.expand_dims(classes_weight_norm, 1)
    y_true = tf.cast(y_true, tf.float64)
    y_pred = tf.clip_by_value(y_pred, epsilon, 1. - epsilon)
    pt = tf.multiply(y_true, y_pred) + tf.multiply(1 - y_true, 1 - y_pred) # 交叉熵
    cross_entropy_loss = -tf.math.log(pt) # 交叉熵损失
    weight = tf.pow(tf.subtract(1., pt), gamma) # 和交叉熵损失一样的性质，交叉熵越大，权重越低,交叉熵损失也是越低
    focal_loss = tf.matmul(tf.multiply(weight, cross_entropy_loss), alpha) # 交叉熵损失*交叉熵损失权重*按类别得到权重(类别权重是数量越多，权重越低)
    loss = tf.reduce_mean(focal_loss)
    return loss
```

```python
y_true = np.random.choice(4,size=[8,1]) # 真实标签 label
y_pred = np.random.normal(size=(8,4)) # 预测的标签 label
```

```python
y_true = tf.cast(keras.utils.to_categorical(y_true),tf.float32)
y_pred = K.softmax(y_pred, axis=1)
```

```python
y_true
```

```python
y_pred
```

```python
loss = multi_category_focal_loss(y_true, y_pred)
```

```python
loss
```

```python

```
