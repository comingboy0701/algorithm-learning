# -*- coding: utf-8 -*-
def reverseString(s):
    """
    :type s: List[str]
    :rtype: None Do not return anything, modify s in-place instead.
    """
    right = 0
    left = len(s)-1
    while (right<left):
        s[right], s[left] = s[left], s[right]
        right = right+1
        left = left-1
    return s


reverseString(["a","b"])

import tensorflow as tf
import numpy as np

# +
'''
inputs是一个形如(batch_size, seq_len, word_size)的张量；
函数返回一个形如(batch_size, seq_len, position_size)的位置张量。
'''

def Position_Embedding(inputs, position_size):
    batch_size,seq_len = tf.shape(inputs)[0],tf.shape(inputs)[1]
    position_j = 1. / tf.pow(10000., 2 * tf.range(position_size / 2, dtype=tf.float32 ) / position_size)
    position_j = tf.expand_dims(position_j, 0)
    position_i = tf.range(tf.cast(seq_len, tf.float32), dtype=tf.float32)
    position_i = tf.expand_dims(position_i, 1)
    position_ij = tf.matmul(position_i, position_j)
    position_ij = tf.concat([tf.cos(position_ij), tf.sin(position_ij)], 1)
    position_embedding = tf.expand_dims(position_ij, 0) + tf.zeros((batch_size, seq_len, position_size))
    return position_embedding


# -

inputs = tf.reshape(tf.range(0,320),[4,80])

inputs.shape

with tf.Session() as sess:
    result = sess.run(Position_Embedding(inputs, 100))

result.shape

result

'''
inputs是一个二阶以上的张量，代表输入序列，比如形如(batch_size, seq_len, input_size)的张量；
seq_len是一个形如(batch_size,)的张量，代表每个序列的实际长度，多出部分都被忽略；
mode分为mul和add，mul是指把多出部分全部置零，一般用于全连接层之前；
add是指把多出部分全部减去一个大的常数，一般用于softmax之前。
'''
def Mask(inputs, seq_len, mode='mul'):
    if seq_len == None:
        return inputs
    else:
        mask = tf.cast(tf.sequence_mask(seq_len), tf.float32)
#         for _ in range(len(inputs.shape)-2):
#         mask = tf.expand_dims(mask, 0)
#         mask = tf.expand_dims(mask, 0)
#         mask = tf.expand_dims(mask, 1)
        if mode == 'mul':
            return inputs * mask
        if mode == 'add':
            return mask#inputs - (1 - mask) * 1e12


inputs = tf.random_normal((4,128*2,80))



with tf.Session() as sess:
    result = sess.run(Mask(inputs, 128, 'add'))

result.shape

result




