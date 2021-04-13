### focal loss 的理解


```python
import numpy as np
import keras
import keras.backend as K
import tensorflow as tf
```


```python
label_num_list = [100,200,50,10] # 假设分类的label有4类，每类的样本数分贝是 100,200,50,10

total_sample_num = np.sum(label_num_list, dtype="float32")

def multi_category_focal_loss(y_true, y_pred):
    
    gamma = 2.0
    epsilon = 1.e-7
    
    y_true = tf.cast(y_true,tf.float32)
    y_pred = tf.cast(y_pred,tf.float32)
    
    classes_weight = [total_sample_num / label_num for label_num in label_num_list]
    classes_weight_norm = [weight / sum(classes_weight) for weight in classes_weight]  # 按类别得到权重进行归一化
    classes_weight_norm = tf.cast(classes_weight_norm, tf.float32) 
    alpha = tf.expand_dims(classes_weight_norm, 1)
    
    y_pred = tf.clip_by_value(y_pred, epsilon, 1. - epsilon)
    pt = tf.multiply(y_true, y_pred) + tf.multiply(1 - y_true, 1 - y_pred) # 交叉熵
    cross_entropy_loss = -K.log(pt) # 交叉熵损失
    weight = tf.pow(tf.subtract(1., pt), gamma) # 和交叉熵损失一样的性质，交叉熵越大，权重越低,交叉熵损失也是越低
    focal_loss = tf.matmul(tf.multiply(weight, cross_entropy_loss), alpha) # 交叉熵损失*交叉熵损失权重*按类别得到权重(类别权重是数量越多，权重越低)
    
    return focal_loss
```


```python
y_true = np.random.choice(4,size=[8]) # 真实标签 label
y_pred = np.random.normal(size=(8,4)) # 预测的标签 label
```


```python
y_true = tf.one_hot(y_true, depth= 4)
y_true
```




    <tf.Tensor: shape=(8, 4), dtype=float32, numpy=
    array([[1., 0., 0., 0.],
           [0., 1., 0., 0.],
           [1., 0., 0., 0.],
           [0., 0., 1., 0.],
           [0., 0., 1., 0.],
           [0., 0., 1., 0.],
           [1., 0., 0., 0.],
           [0., 1., 0., 0.]], dtype=float32)>




```python
y_pred = K.softmax(y_pred,axis=-1)
y_pred
```




    <tf.Tensor: shape=(8, 4), dtype=float64, numpy=
    array([[0.37623284, 0.33230947, 0.0481559 , 0.2433018 ],
           [0.29497108, 0.12360882, 0.20553146, 0.37588864],
           [0.58799838, 0.28331014, 0.07566421, 0.05302727],
           [0.4794889 , 0.07500778, 0.41214537, 0.03335796],
           [0.28295068, 0.07871247, 0.16113049, 0.47720636],
           [0.03239087, 0.35946281, 0.28374307, 0.32440325],
           [0.08932401, 0.54083959, 0.23610655, 0.13372985],
           [0.19806993, 0.20598081, 0.29593526, 0.30001401]])>




```python
loss = multi_category_focal_loss(y_true, y_pred)
```


```python
loss
```




    <tf.Tensor: shape=(8, 1), dtype=float32, numpy=
    array([[0.04206767],
           [0.11250415],
           [0.00784752],
           [0.05654315],
           [0.30171221],
           [0.12844518],
           [0.16094631],
           [0.06586922]], dtype=float32)>



```python

```
