# -*- coding:utf-8 -*-
import re
import numpy as np


sents = open('/Users/coming/datasets/icwb2-data/training/msr_training.utf8').read()
# sents = sents.decode('utf-8').strip()
sents = sents.strip()
# sents = sents.split('\r\n') # 这个语料的换行符是\r\n
sents = sents.split('\n') # 这个语料的换行符是\r\n

sents = [re.split(' +', s) for s in sents] # 词之间以空格隔开
sents = [[w for w in s if w] for s in sents] # 去掉空字符串
np.random.shuffle(sents) # 打乱语料，以便后面划分验证集

chars = {} # 统计字表
for s in sents:
    for c in ''.join(s):
        if c in chars:
            chars[c] += 1
        else:
            chars[c] = 1

min_count = 2 # 过滤低频字
chars = {i:j for i,j in chars.items() if j >= min_count} # 过滤低频字
id2char = {i+1:j for i,j in enumerate(chars)} # id到字的映射
char2id = {j:i for i,j in id2char.items()} # 字到id的映射

id2tag = {0:'s', 1:'b', 2:'m', 3:'e'} # 标签（sbme）与id之间的映射
tag2id = {j:i for i,j in id2tag.items()}

train_sents = sents[:-5000] # 留下5000个句子做验证，剩下的都用来训练
valid_sents = sents[-5000:]


from keras.utils import to_categorical

batch_size = 128
def train_generator(): # 定义数据生成器
    while True:
        X,Y = [],[]
        for i,s in enumerate(train_sents): # 遍历每个句子
            sx,sy = [],[]
            for w in s: # 遍历句子中的每个词
                sx.extend([char2id.get(c, 0) for c in w]) # 遍历词中的每个字
                if len(w) == 1:
                    sy.append(0) # 单字词的标签
                elif len(w) == 2:
                    sy.extend([1,3]) # 双字词的标签
                else:
                    sy.extend([1] + [2]*(len(w)-2) + [3]) # 多于两字的词的标签
            X.append(sx)
            Y.append(sy)
            if len(X) == batch_size or i == len(train_sents)-1: # 如果达到一个batch
                maxlen = max([len(x) for x in X]) # 找出最大字数
                X = [x+[0]*(maxlen-len(x)) for x in X] # 不足则补零
                Y = [y+[4]*(maxlen-len(y)) for y in Y] # 不足则补第五个标签
                yield np.array(X),to_categorical(Y, 5)
                X,Y = [],[]


data_generator = train_generator()

x, y = next(data_generator)

x.shape

y.shape

from crf_keras import CRF
from keras.layers import Dense, Embedding, Conv1D, Input
from keras.models import Model # 这里我们学习使用Model型的模型
import keras.backend as K # 引入Keras后端来自定义loss，注意Keras模型内的一切运算
# 必须要通过Keras后端完成，比如取对数要用K.log不能用np.log

embedding_size = 128
sequence = Input(shape=(None,), dtype='int32') # 建立输入层，输入长度设为None
embedding = Embedding(len(chars)+1,embedding_size)(sequence) # 去掉了mask_zero=True
cnn = Conv1D(128, 3, activation='relu', padding='same')(embedding)
cnn = Conv1D(128, 3, activation='relu', padding='same')(cnn)
cnn = Conv1D(128, 3, activation='relu', padding='same')(cnn) # 层叠了3层CNN

crf = CRF(True) # 定义crf层，参数为True，自动mask掉最后一个标签
tag_score = Dense(5)(cnn) # 变成了5分类，第五个标签用来mask掉
tag_score = crf(tag_score) # 包装一下原来的tag_score

model = Model(inputs=sequence, outputs=tag_score)
model.summary()

model.compile(loss=crf.loss, # 用crf自带的loss
              optimizer='adam',
              metrics=[crf.accuracy] # 用crf自带的accuracy
             )


def max_in_dict(d): # 定义一个求字典中最大值的函数
    key,value = list(d.items())[0]
    for i,j in list(d.items())[1:]:
        if j > value:
            key,value = i,j
    return key,value


def viterbi(nodes, trans): # viterbi算法，跟前面的HMM一致
    paths = nodes[0] # 初始化起始路径
    for l in range(1, len(nodes)): # 遍历后面的节点
        paths_old,paths = paths,{}
        for n,ns in nodes[l].items(): # 当前时刻的所有节点
            max_path,max_score = '',-1e10
            for p,ps in paths_old.items(): # 截止至前一时刻的最优路径集合
                score = ns + ps + trans[p[-1]+n] # 计算新分数
                if score > max_score: # 如果新分数大于已有的最大分
                    max_path,max_score = p+n, score # 更新路径
            paths[max_path] = max_score # 储存到当前时刻所有节点的最优路径
    return max_in_dict(paths)


def cut(s, trans): # 分词函数，也跟前面的HMM基本一致
    if not s: # 空字符直接返回
        return []
    # 字序列转化为id序列。注意，经过我们前面对语料的预处理，字符集是没有空格的，
    # 所以这里简单将空格的id跟句号的id等同起来
    sent_ids = np.array([[char2id.get(c, 0) if c != ' ' else char2id[u'。']
                          for c in s]])
    probas = model.predict(sent_ids)[0] # 模型预测
    nodes = [dict(zip('sbme', i)) for i in probas[:, :4]] # 只取前4个
    nodes[0] = {i:j for i,j in nodes[0].items() if i in 'bs'} # 首字标签只能是b或s
    nodes[-1] = {i:j for i,j in nodes[-1].items() if i in 'es'} # 末字标签只能是e或s
    tags = viterbi(nodes, trans)[0]
    result = [s[0]]
    for i,j in zip(s[1:], tags[1:]):
        if j in 'bs': # 词的开始
            result.append(i)
        else: # 接着原来的词
            result[-1] += i
    return result


from keras.callbacks import Callback
from tqdm import tqdm

# 自定义Callback类
class Evaluate(Callback):
    def __init__(self):
        self.highest = 0.
    def on_epoch_end(self, epoch, logs=None):
        _ = model.get_weights()[-1][:4,:4] # 从训练模型中取出最新得到的转移矩阵
        trans = {}
        for i in 'sbme':
            for j in 'sbme':
                trans[i+j] = _[tag2id[i], tag2id[j]]
        right = 0.
        total = 0.
        for s in tqdm(iter(valid_sents), desc=u'验证模型中'):
            result = cut(''.join(s), trans)
            total += len(set(s))
            right += len(set(s) & set(result)) # 直接将词集的交集作为正确数。该指标比较简单，
                                               # 也许会导致估计偏高。读者可以考虑自定义指标
        acc = right/total
        if acc > self.highest:
            self.highest = acc
        print ('val acc: %s, highest: %s'%(acc, self.highest))


evaluator = Evaluate() # 建立Callback类
model.fit_generator(train_generator(),
                    steps_per_epoch=100,
                    epochs=1,
                    callbacks=[evaluator]) # 训练并将evaluator加入到训练过程

_trans = model.get_weights()[-1][:4,:4] # 从训练模型中取出最新得到的转移矩阵

_trans

s= ["我喜欢自然语言处理","工具人加油"]
test_sents = [['我', '喜欢', '自然', '语言', '处理', '工作'], ['工具人', '加油']]
x_true,y_true = [],[]
for i,s in enumerate(test_sents): # 遍历每个句子
    sx,sy = [],[]
    for w in s: # 遍历句子中的每个词
        sx.extend([char2id.get(c, 0) for c in w]) # 遍历词中的每个字
        if len(w) == 1:
            sy.append(0) # 单字词的标签
        elif len(w) == 2:
            sy.extend([1,3]) # 双字词的标签
        else:
            sy.extend([1] + [2]*(len(w)-2) + [3]) # 多于两字的词的标签
    x_true.append(sx)
    y_true.append(sy)
maxlen = max([len(x) for x in X]) # 找出最大字数
x_true = [x+[0]*(maxlen-len(x)) for x in x_true] # 不足则补零
y_true = [y+[4]*(maxlen-len(y)) for y in y_true] # 不足则补第五个标签
x_true = np.array(x_true)
y_true = to_categorical(y_true, 5)

x_true.shape, y_true.shape

x_true

y_true

y_pred = model.predict(x_true) # 模型预测

y_pred

mask = 1 - y_true[:, :, -1:]

mask

y_true, y_pred = y_true[:, :, :4], y_pred[:, :, :4]

y_true

y_pred

# 真实标签和真实路径的得分
point_score = K.sum(K.sum(y_pred * y_true, 2), 1, keepdims=True)  # 逐标签得分
labels1 = K.expand_dims(y_true[:, :-1], 3)
labels2 = K.expand_dims(y_true[:, 1:], 2)
labels = labels1 * labels2  # 两个错位labels，负责从转移矩阵中抽取目标转移得分
trans = K.expand_dims(K.expand_dims(_trans, 0), 0)
trans_score = K.sum(K.sum(trans * labels, [2, 3]), 1, keepdims=True)

# 全部标签和全部路径的分数
path_score = point_score + trans_score
path_score
init_states = [y_pred[:, 0]]  # 初始状态

init_states

y_pred.shape, mask.shape

y_pred = np.concatenate([y_pred, mask], axis=-1)


def log_norm_step(inputs, states):
    """递归计算归一化因子
    要点：1、递归计算；2、用logsumexp避免溢出。
    技巧：通过expand_dims来对齐张量。
    """
    inputs, mask = inputs[:, :-1], inputs[:, -1:]
    states = K.expand_dims(states[0], 2)  # (batch_size, output_dim, 1)
    trans = K.expand_dims(_trans, 0)  # (1, output_dim, output_dim)
    outputs = K.logsumexp(states + trans, 1)  # (batch_size, output_dim)
    outputs = outputs + inputs
    outputs = mask * outputs + (1 - mask) * states[:, :, 0] # 如果是mask,那就不转移了，直接取status的分数
    return outputs, [outputs]


last_output, outputs, states = K.rnn(log_norm_step, y_pred[:, 1:], init_states)  # 计算Z向量（对数）
log_norm = K.logsumexp(last_output, 1, keepdims=True)  # 计算Z（对数）

log_norm


