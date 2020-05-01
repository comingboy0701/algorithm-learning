# 4-3,AutoGraph的使用规范

有三种计算图的构建方式：静态计算图，动态计算图，以及Autograph。

TensorFlow 2.0主要使用的是动态计算图和Autograph。

动态计算图易于调试，编码效率较高，但执行效率偏低。

静态计算图执行效率很高，但较难调试。

而Autograph机制可以将动态图转换成静态计算图，兼收执行效率和编码效率之利。

当然Autograph机制能够转换的代码并不是没有任何约束的，有一些编码规范需要遵循，否则可能会转换失败或者不符合预期。

我们将着重介绍Autograph的编码规范和Autograph转换成静态图的原理。

并介绍使用tf.Module来更好地构建Autograph。

本篇我们介绍使用Autograph的编码规范。

<!-- #region -->
### 一，Autograph编码规范总结


* 1，被@tf.function修饰的函数应尽可能使用TensorFlow中的函数而不是Python中的其他函数。例如使用tf.print而不是print，使用tf.range而不是range，使用tf.constant(True)而不是True.

* 2，避免在@tf.function修饰的函数内部定义tf.Variable. 

* 3，被@tf.function修饰的函数不可修改该函数外部的Python列表或字典等数据结构变量。
<!-- #endregion -->

### 二，Autograph编码规范解析


 **1，被@tf.function修饰的函数应尽量使用TensorFlow中的函数而不是Python中的其他函数。**

```python
import numpy as np
import tensorflow as tf

```

```python
@tf.function
def np_random():
    a = np.random.randn(3,3)
    tf.print(a)
    
@tf.function
def tf_random():
    a = tf.random.normal((3,3))
    tf.print(a)
```

```python
#np_random每次执行都是一样的结果。
np_random()
np_random()

tf.print("=="*8)
tf_random()
tf_random()
```

```python

```

**2，避免在@tf.function修饰的函数内部定义tf.Variable.**

```python
# 避免在@tf.function修饰的函数内部定义tf.Variable.

x = tf.Variable(1.0,dtype=tf.float32)
@tf.function
def outer_var():
    x.assign_add(1.0)
    tf.print(x)
    return(x)

outer_var() 
outer_var()

```

```python
@tf.function
def inner_var():
    x = tf.Variable(1.0,dtype = tf.float32)
    x.assign_add(1.0)
    tf.print(x)
    return(x)

#执行将报错
#inner_var()
#inner_var()
```

**3,被@tf.function修饰的函数不可修改该函数外部的Python列表或字典等结构类型变量。**

```python
tensor_list = []
# @tf.function #加上这一行切换成Autograph结果将不符合预期！！！
def append_tensor(x):
    tensor_list.append(x)
    return tensor_list

append_tensor(tf.constant(5.0))
append_tensor(tf.constant(6.0))
append_tensor(tf.constant(7.0))

tf.print(tensor_list)
```
