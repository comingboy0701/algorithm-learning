# 4-5,AutoGraph和tf.Module


有三种计算图的构建方式：静态计算图，动态计算图，以及Autograph。

TensorFlow 2.0主要使用的是动态计算图和Autograph。

动态计算图易于调试，编码效率较高，但执行效率偏低。

静态计算图执行效率很高，但较难调试。

而Autograph机制可以将动态图转换成静态计算图，兼收执行效率和编码效率之利。

当然Autograph机制能够转换的代码并不是没有任何约束的，有一些编码规范需要遵循，否则可能会转换失败或者不符合预期。

前面我们介绍了Autograph的编码规范和Autograph转换成静态图的原理。

本篇我们介绍使用tf.Module来更好地构建Autograph。




### 一，Autograph和tf.Module概述


前面在介绍Autograph的编码规范时提到构建Autograph时应该避免在@tf.function修饰的函数内部定义tf.Variable. 

但是如果在函数外部定义tf.Variable的话，又会显得这个函数有外部变量依赖，封装不够完美。

一种简单的思路是定义一个类，并将相关的tf.Variable创建放在类的初始化方法中。而将函数的逻辑放在其他方法中。

这样一顿猛如虎的操作之后，我们会觉得一切都如同人法地地法天天法道道法自然般的自然。

惊喜的是，TensorFlow提供了一个基类tf.Module，通过继承它构建子类，我们不仅可以获得以上的自然而然，而且可以非常方便地管理变量，还可以非常方便地管理它引用的其它Module，最重要的是，我们能够利用tf.saved_model保存模型并实现跨平台部署使用。

实际上，tf.keras.models.Model,tf.keras.layers.Layer 都是继承自tf.Module的，提供了方便的变量管理和所引用的子模块管理的功能。

**因此，利用tf.Module提供的封装，再结合TensoFlow丰富的低阶API，实际上我们能够基于TensorFlow开发任意机器学习模型(而非仅仅是神经网络模型)，并实现跨平台部署使用。**





### 二，应用tf.Module封装Autograph


m定义一个简单的function。

```python
import tensorflow as tf
x = tf.Variable(1.0,dtype = tf.float32)

#在tf.function中用input_signature限定输入张量的签名类型：shape和dtype
@tf.function(input_signature=[tf.TensorSpec(shape=[],dtype=tf.float32)])
def add_print(a):
    x.assign_add(a)
    tf.print(x)
    return (x)
```

```python
add_print(tf.constant(3.0))
```

下面利用tf.Module的子类化将其封装一下。

```python
class DemoModel(tf.Module):
    def __init__(self,init_value=tf.constant(0.0),name=None):
        super(DemoModel,self).__init__(name=name)
        with self.name_scope:
            self.x = tf.Variable(init_value,dtype=tf.float32,trainable = True)
    
    @tf.function(input_signature=[tf.TensorSpec(shape = [], dtype = tf.float32)])  
    def addprint(self,a):
        with self.name_scope:
            self.x.assign_add(a)
            tf.print(self.x)
            return self.x
```

```python
#执行
demo = DemoModel(init_value=tf.constant(1.0))
result = demo.addprint(tf.constant(5.0))
```

```python
#查看模块中的全部变量和全部可训练变量
print(demo.variables)
print(demo.trainable_variables)
```

```python
#查看模块中的全部子模块
demo.submodules
```

```python
import os
```

```python
#使用tf.saved_model 保存模型，并指定需要跨平台部署的方法
tf.saved_model.save(demo,".\\data\\demo\\1",signatures = {"serving_default":demo.addprint})
```

```python
#加载模型
demo2 = tf.saved_model.load(".\\data\\demo\\1")
demo2.addprint(tf.constant(5.0))
```

```python
# 查看模型文件相关信息，红框标出来的输出信息在模型部署和跨平台使用时有可能会用到
!saved_model_cli show --dir ./data/demo/1 --all
```

```python
#启动 tensorboard在jupyter中的魔法命令
%reload_ext tensorboard
```

```python
from tensorboard import notebook
notebook.list() 
```

```python
notebook.start("--logdir ./data/demomodule/")
```

除了利用tf.Module的子类化实现封装，我们也可以通过给tf.Module添加属性的方法进行封装。

```python

mymodule = tf.Module()
mymodule.x = tf.Variable(0.0)
@tf.function(input_signature=[tf.TensorSpec(shape = [], dtype = tf.float32)])  
def addprint(a):
    mymodule.x.assign_add(a)
    tf.print(mymodule.x)
    return (mymodule.x)
mymodule.addprint = addprint

```

```python
mymodule.addprint(tf.constant(4.0)).numpy()
```

```python
tf.print(mymodule.variables)
```

```python
#使用tf.saved_model 保存模型
tf.saved_model.save(mymodule,"./data/mymodule",
    signatures = {"serving_default":mymodule.addprint})

#加载模型
mymodule2 = tf.saved_model.load(".\\data\\mymodule")
mymodule2.addprint(tf.constant(5.0))
```

### 三，tf.Module和tf.keras.Model，tf.keras.layers.Layer


tf.keras中的模型和层都是继承tf.Module实现的，也具有变量管理和子模块管理功能。

```python
import tensorflow as tf
from tensorflow.keras import models,layers,losses,metrics
```

```python

```
