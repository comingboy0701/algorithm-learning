## 1 基本概念

* 优先使用函数式API，而不是keras中其他风格的创建模型的方法
* 优先使用keras预定义的fit/fit_generator方法，而不是自定义训练流程

## 2 张量

张量（多维数组）,包含
* 0维：标量
* 1维：向量
* 2维：矩阵
* 3维及更高的维度

构成张量的要素：
* ndim：轴的个数（阶）
* shape：形状，在构建模型阶段检查模型正确性的主要手段
* dtype：数据类型，int/float

实现张量的数据结构：
* numpy中的ndarray类型
* tensorflow中的Tensor类型
* python中原生的list

ndarray和Tensor的使用差异不大；但是它们和list的差异很大

## 3 keras 

* Layer: keras.layers.Layer/基础组件
* Model: keras.models.Model/多个层构成的网络，可以看成一种特殊的Layer
* 输入的数据（x）和相应的目标（y）
* 损失函数: keras.losses.Loss
* 优化器: keras.optimizers.Optimizer


```python
import tensorflow as tf
import keras 
import numpy as np

print("tf.version={}".format(tf.__version__))
print("keras.version={}".format(keras.__version__))
```

    tf.version=1.14.0
    keras.version=2.3.1


实现一个模型，通常分三步：
1. create_model（建议先在jupyter里写好）
2. train（建议先在jupyter里写好）
3. predict (建议先在jupyter里写好)

- 总而言之，建议先在jupyter里测完，再移到代码库里去，除非你特别熟练

## 4 keras开发示例

 考虑一个文本的二分类问题，文本中包含有0和1两种字符，输入如下:


```python
# 输入 x >> 数据
x_raw = [
    "00001", 
    "001000101",
    "001011101110",
    "0101010100",
    "0113410101010",
    "01210101011"
]
vocab_size = 2 # 假设词典中只有两个词0和1；oov用2表示，padding用3表示
seq_length = 16 # 序列长度标准化为16，超过截断，不足补3
x_tensor = []
for x_text in x_raw:
    x_segs =[int(w) if w in ('0', '1') else 2 for w in list(x_text)]
    padding = [3] * (seq_length-len(x_segs))
    x_tensor.append(x_segs + padding)
x_tensor = np.array(x_tensor)
print("x_tensor=\n{}".format(x_tensor))
```

    x_tensor=
    [[0 0 0 0 1 3 3 3 3 3 3 3 3 3 3 3]
     [0 0 1 0 0 0 1 0 1 3 3 3 3 3 3 3]
     [0 0 1 0 1 1 1 0 1 1 1 0 3 3 3 3]
     [0 1 0 1 0 1 0 1 0 0 3 3 3 3 3 3]
     [0 1 1 2 2 1 0 1 0 1 0 1 0 3 3 3]
     [0 1 2 1 0 1 0 1 0 1 1 3 3 3 3 3]]



```python
# 输入y数据 >> 假设输入中1的个数多输出则为1，0的个数多则为0
y_raw = []
for x_text in x_raw:
    count_0 = len([w for w in list(x_text) if w == '0' ])
    count_1 = len([w for w in list(x_text) if w == '1' ])
    if count_0 > count_1:
        y_raw.append([1])
    else:
        y_raw.append([0])
y_tensor = np.array(y_raw)
print("y_tensor=\n{}".format(y_tensor))
```

    y_tensor=
    [[1]
     [1]
     [0]
     [1]
     [0]
     [0]]



```python
# 创建模型
def create_model():

    input_tensor = keras.Input(shape=(16,), dtype=tf.int32, name='input')
    print("input_tensor:{}".format(input_tensor))

    embedded = keras.layers.Embedding(vocab_size + 2, 64)(input_tensor)
    print("embedded:{}".format(embedded))

    sentence_encoding = keras.layers.LSTM(64)(embedded)
    print("sentence_encoding:{}".format(sentence_encoding))

    # 注意二分类使用sigmoid激活函数，binary_crossentropy损失和binary_accuracy评估函数（y_true是一维）
    # 二分类也可以使用softmax激活函数，categorical_crossentropy损失和categorical_accuracy评估函数（y_true是二维））

    output_tensor = keras.layers.Dense(units=1, activation='sigmoid')(sentence_encoding)
    print("output_tensor:{}".format(output_tensor))

    model = keras.models.Model(inputs=[input_tensor], outputs=[output_tensor])
    
    model.compile(
    optimizer=keras.optimizers.Adam(5e-2), 
    loss=keras.losses.binary_crossentropy,
    metrics=[keras.metrics.binary_accuracy])
    
    return model

model = create_model()
    
model.summary()

# 训练模型使用 fit
model.fit(x=x_tensor, y=y_tensor, epochs=10, batch_size=4)

# 预测模型
model.predict(x_tensor)
```

    input_tensor:Tensor("input_26:0", shape=(?, 16), dtype=int32)
    embedded:Tensor("embedding_31/embedding_lookup/Identity_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding:Tensor("lstm_26/strided_slice_18:0", shape=(?, 64), dtype=float32)
    output_tensor:Tensor("dense_31/Sigmoid:0", shape=(?, 1), dtype=float32)
    Model: "model_30"
    _________________________________________________________________
    Layer (type)                 Output Shape              Param #   
    =================================================================
    input (InputLayer)           (None, 16)                0         
    _________________________________________________________________
    embedding_31 (Embedding)     (None, 16, 64)            256       
    _________________________________________________________________
    lstm_26 (LSTM)               (None, 64)                33024     
    _________________________________________________________________
    dense_31 (Dense)             (None, 1)                 65        
    =================================================================
    Total params: 33,345
    Trainable params: 33,345
    Non-trainable params: 0
    _________________________________________________________________
    Epoch 1/10
    6/6 [==============================] - 1s 102ms/step - loss: 0.6499 - binary_accuracy: 0.5000
    Epoch 2/10
    6/6 [==============================] - 0s 2ms/step - loss: 1.1965 - binary_accuracy: 0.5000
    Epoch 3/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.7714 - binary_accuracy: 0.3333
    Epoch 4/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.6743 - binary_accuracy: 0.8333
    Epoch 5/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.8190 - binary_accuracy: 0.5000
    Epoch 6/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.7630 - binary_accuracy: 0.5000
    Epoch 7/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.6350 - binary_accuracy: 0.6667
    Epoch 8/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.7282 - binary_accuracy: 0.5000
    Epoch 9/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.6880 - binary_accuracy: 0.5000
    Epoch 10/10
    6/6 [==============================] - 0s 3ms/step - loss: 0.6500 - binary_accuracy: 0.5000

    array([[0.77727914],
           [0.54700667],
           [0.39280307],
           [0.42077434],
           [0.3757507 ],
           [0.42479986]], dtype=float32)



## 5 fit_generator


```python
class TrainDataGenerator(object):
    def __init__(self, x_tensor, y_tensor):
        self.x_tensor = x_tensor
        self.y_tensor = y_tensor
    
    def for_fit(self):
        while True:
            for x, y in zip(self.x_tensor, self.y_tensor):
                yield np.array([x]), np.array([y])
    
train_gen = TrainDataGenerator(x_tensor, y_tensor)
        
model.fit_generator(generator=train_gen.for_fit(), steps_per_epoch=30, epochs=5)
```

    Epoch 1/5
    30/30 [==============================] - 0s 5ms/step - loss: 0.7247 - binary_accuracy: 0.6667
    Epoch 2/5
    30/30 [==============================] - 0s 5ms/step - loss: 0.5883 - binary_accuracy: 0.7000
    Epoch 3/5
    30/30 [==============================] - 0s 5ms/step - loss: 0.3554 - binary_accuracy: 0.8667
    Epoch 4/5
    30/30 [==============================] - 0s 5ms/step - loss: 0.6527 - binary_accuracy: 0.6667
    Epoch 5/5
    30/30 [==============================] - 0s 5ms/step - loss: 0.7228 - binary_accuracy: 0.5333

    <keras.callbacks.callbacks.History at 0x150b617f0>



## 6 Lambda表达式

* 如果要做的运算很复杂，在lambda表达式里面写不下怎么办
* 把运算抽出来做一个函数，在lambda表达式里面调用这个函数
* 继承keras.layers.Layer接口，实现一个自定义的layer


```python
input_tensor = keras.Input(shape=(16,), dtype=tf.int32, name='input')
print("input_tensor:{}".format(input_tensor))

embedded = keras.layers.Embedding(vocab_size + 2, 64)(input_tensor)
print("embedded:{}".format(embedded))

sentence_encoding = keras.layers.LSTM(units=64, return_sequences=True)(embedded)
print("sentence_encoding:{}".format(sentence_encoding))

# sentence_pooling = tf.reduce_sum(sentence_encoding, axis=1) 必须再用 Lambda 包装一下
# print("sentence_pooling:{}".format(sentence_pooling))

sentence_pooling = keras.layers.Lambda(lambda x: tf.reduce_sum(x, axis=1))(sentence_encoding)
print("sentence_pooling:{}".format(sentence_pooling))

output_tensor = keras.layers.Dense(units=2, activation='softmax')(sentence_pooling)
print("output_tensor:{}".format(output_tensor))

model = keras.models.Model(inputs=[input_tensor], outputs=[output_tensor])

model.summary()
```

    input_tensor:Tensor("input_27:0", shape=(?, 16), dtype=int32)
    embedded:Tensor("embedding_32/embedding_lookup/Identity_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding:Tensor("lstm_27/transpose_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_pooling:Tensor("lambda_10/Sum:0", shape=(?, 64), dtype=float32)
    output_tensor:Tensor("dense_32/Softmax:0", shape=(?, 2), dtype=float32)
    Model: "model_31"
    _________________________________________________________________
    Layer (type)                 Output Shape              Param #   
    =================================================================
    input (InputLayer)           (None, 16)                0         
    _________________________________________________________________
    embedding_32 (Embedding)     (None, 16, 64)            256       
    _________________________________________________________________
    lstm_27 (LSTM)               (None, 16, 64)            33024     
    _________________________________________________________________
    lambda_10 (Lambda)           (None, 64)                0         
    _________________________________________________________________
    dense_32 (Dense)             (None, 2)                 130       
    =================================================================
    Total params: 33,410
    Trainable params: 33,410
    Non-trainable params: 0
    _________________________________________________________________


## 7 参数共享


```python
input_tensor = keras.Input(shape=(16,), dtype=tf.int32, name='input')
print("input_tensor:{}".format(input_tensor))

embedded = keras.layers.Embedding(vocab_size + 2, 64)(input_tensor)
print("embedded:{}".format(embedded))

lstm = keras.layers.LSTM(units=64, return_sequences=True, name='lstm-encoder')

sentence_encoding_lstm_1 = lstm(embedded)
print("sentence_encoding_lstm_1:{}".format(sentence_encoding_lstm_1))

sentence_encoding_lstm_2 = lstm(sentence_encoding_lstm_1)
print("sentence_encoding_lstm_2:{}".format(sentence_encoding_lstm_2))

sentence_encoding_lstm_3 = lstm(sentence_encoding_lstm_2)
print("sentence_encoding_lstm_2:{}".format(sentence_encoding_lstm_3))

sentence_pooling = keras.layers.Lambda(lambda x: tf.reduce_sum(x, axis=1))(sentence_encoding_lstm_3)
print("sentence_pooling:{}".format(sentence_pooling))

output_tensor = keras.layers.Dense(units=2, activation='softmax')(sentence_pooling)
print("output_tensor:{}".format(output_tensor))

model = keras.models.Model(inputs=[input_tensor], outputs=[output_tensor])

model.summary()
```

    input_tensor:Tensor("input_28:0", shape=(?, 16), dtype=int32)
    embedded:Tensor("embedding_33/embedding_lookup/Identity_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding_lstm_1:Tensor("lstm-encoder_15/transpose_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding_lstm_2:Tensor("lstm-encoder_16/transpose_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding_lstm_2:Tensor("lstm-encoder_17/transpose_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_pooling:Tensor("lambda_11/Sum:0", shape=(?, 64), dtype=float32)
    output_tensor:Tensor("dense_33/Softmax:0", shape=(?, 2), dtype=float32)
    Model: "model_32"
    __________________________________________________________________________________________________
    Layer (type)                    Output Shape         Param #     Connected to                     
    ==================================================================================================
    input (InputLayer)              (None, 16)           0                                            
    __________________________________________________________________________________________________
    embedding_33 (Embedding)        (None, 16, 64)       256         input[0][0]                      
    __________________________________________________________________________________________________
    lstm-encoder (LSTM)             (None, 16, 64)       33024       embedding_33[0][0]               
                                                                     lstm-encoder[0][0]               
                                                                     lstm-encoder[1][0]               
    __________________________________________________________________________________________________
    lambda_11 (Lambda)              (None, 64)           0           lstm-encoder[2][0]               
    __________________________________________________________________________________________________
    dense_33 (Dense)                (None, 2)            130         lambda_11[0][0]                  
    ==================================================================================================
    Total params: 33,410
    Trainable params: 33,410
    Non-trainable params: 0
    __________________________________________________________________________________________________


## 8 多输入模型


```python
input_tensor_1 = keras.Input(shape=(16,), dtype=tf.int32, name='input_1')
print("input_tensor_1:{}".format(input_tensor_1))

input_tensor_2 = keras.Input(shape=(16,), dtype=tf.int32, name='input_2')
print("input_tensor_2:{}".format(input_tensor_2))

input_tensor_concat = keras.layers.concatenate([input_tensor_1, input_tensor_2])
print("input_tensor_concat:{}".format(input_tensor_concat))

embedded = keras.layers.Embedding(vocab_size + 2, 64)(input_tensor_concat)
print("embedded:{}".format(embedded))

sentence_encoding = keras.layers.LSTM(64)(embedded)
print("sentence_encoding:{}".format(sentence_encoding))

output_tensor = keras.layers.Dense(units=2, activation='softmax')(sentence_encoding)
print("output_tensor:{}".format(output_tensor))

model = keras.models.Model(inputs=[input_tensor_1, input_tensor_2], outputs=[output_tensor])

model.compile(
    optimizer=keras.optimizers.Adam(5e-2), 
    loss=keras.losses.categorical_crossentropy,
    metrics=[keras.metrics.categorical_accuracy]
)

model.summary()
```

    input_tensor_1:Tensor("input_1_5:0", shape=(?, 16), dtype=int32)
    input_tensor_2:Tensor("input_2_5:0", shape=(?, 16), dtype=int32)
    input_tensor_concat:Tensor("concatenate_5/concat:0", shape=(?, 32), dtype=int32)
    embedded:Tensor("embedding_34/embedding_lookup/Identity_1:0", shape=(?, 32, 64), dtype=float32)
    sentence_encoding:Tensor("lstm_28/strided_slice_18:0", shape=(?, 64), dtype=float32)
    output_tensor:Tensor("dense_34/Softmax:0", shape=(?, 2), dtype=float32)
    Model: "model_33"
    __________________________________________________________________________________________________
    Layer (type)                    Output Shape         Param #     Connected to                     
    ==================================================================================================
    input_1 (InputLayer)            (None, 16)           0                                            
    __________________________________________________________________________________________________
    input_2 (InputLayer)            (None, 16)           0                                            
    __________________________________________________________________________________________________
    concatenate_5 (Concatenate)     (None, 32)           0           input_1[0][0]                    
                                                                     input_2[0][0]                    
    __________________________________________________________________________________________________
    embedding_34 (Embedding)        (None, 32, 64)       256         concatenate_5[0][0]              
    __________________________________________________________________________________________________
    lstm_28 (LSTM)                  (None, 64)           33024       embedding_34[0][0]               
    __________________________________________________________________________________________________
    dense_34 (Dense)                (None, 2)            130         lstm_28[0][0]                    
    ==================================================================================================
    Total params: 33,410
    Trainable params: 33,410
    Non-trainable params: 0
    __________________________________________________________________________________________________


## 9 多输出模型

* 和多任务关系密切，以后专门讨论

## 10 回调函数

回调函数的主要用途：
* checkpoint
* early stopping
* 训练中动态调节参数值，比如学习率
* 训练中记录训练指标和验证指标，比如训练中的进度条

回调函数的实现方式：
* 实现keras.callbacks.Callback接口
* on_epoch_begin
* on_epoch_end
* on_batch_begin
* on_batch_end
* on_train_begin
* on_train_end


```python
class HelloWorldCallback(keras.callbacks.Callback):
    def on_epoch_begin(self, epoch, logs=None):
        print("epoch:{} begin\n".format(epoch))
        
    def on_epoch_end(self, epoch, logs=None):
        print("epoch:{} end\n".format(epoch))
        
model = create_model()
callbacks = [HelloWorldCallback()]
model.fit(x=x_tensor, y=y_tensor, epochs=5, batch_size=4, callbacks=callbacks)
```

    input_tensor:Tensor("input_30:0", shape=(?, 16), dtype=int32)
    embedded:Tensor("embedding_36/embedding_lookup/Identity_1:0", shape=(?, 16, 64), dtype=float32)
    sentence_encoding:Tensor("lstm_30/strided_slice_18:0", shape=(?, 64), dtype=float32)
    output_tensor:Tensor("dense_36/Sigmoid:0", shape=(?, 1), dtype=float32)
    Epoch 1/5
    epoch:0 begin
    
    6/6 [==============================] - 1s 101ms/step - loss: 0.7532 - binary_accuracy: 0.5000
    epoch:0 end
    
    Epoch 2/5
    epoch:1 begin
    
    6/6 [==============================] - 0s 3ms/step - loss: 0.7685 - binary_accuracy: 0.6667
    epoch:1 end
    
    Epoch 3/5
    epoch:2 begin
    
    6/6 [==============================] - 0s 3ms/step - loss: 0.6480 - binary_accuracy: 0.5000
    epoch:2 end
    
    Epoch 4/5
    epoch:3 begin
    
    6/6 [==============================] - 0s 3ms/step - loss: 0.4163 - binary_accuracy: 0.8333
    epoch:3 end
    
    Epoch 5/5
    epoch:4 begin
    
    6/6 [==============================] - 0s 3ms/step - loss: 0.6782 - binary_accuracy: 0.5000
    epoch:4 end

    <keras.callbacks.callbacks.History at 0x1534a51d0>




```python
22*12+16*12*22/1000+1*12+30*12/1000+2.5*22+700*4/1000
```

```python
24*12+24*12*0.12+2*24
```

```python
24*12*0.12
```
