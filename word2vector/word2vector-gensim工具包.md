## 1 在gensim中，word2vec 相关的API都在包gensim.models.word2vec中。和算法有关的参数都在类gensim.models.word2vec.Word2Vec中。算法需要注意的参数有：

1) sentences: 我们要分析的语料，可以是一个列表，或者从文件中遍历读出。后面我们会有从文件读出的例子。

2) size: 词向量的维度，默认值是100。这个维度的取值一般与我们的语料的大小相关，如果是不大的语料，比如小于100M的文本语料，则使用默认值一般就可以了。如果是超大的语料，建议增大维度。

3) window：即词向量上下文最大距离，这个参数在我们的算法原理篇中标记为𝑐，window越大，则和某一词较远的词也会产生上下文关系。默认值为5。在实际使用中，可以根据实际的需求来动态调整这个window的大小。如果是小语料则这个值可以设的更小。对于一般的语料这个值推荐在[5,10]之间。

4) sg: 即我们的word2vec两个模型的选择了。如果是0， 则是CBOW模型，是1则是Skip-Gram模型，默认是0即CBOW模型。

5) hs: 即我们的word2vec两个解法的选择了，如果是0， 则是Negative Sampling，是1的话并且负采样个数negative大于0， 则是Hierarchical Softmax。默认是0即Negative Sampling。

6) negative:即使用Negative Sampling时负采样的个数，默认是5。推荐在[3,10]之间。这个参数在我们的算法原理篇中标记为neg。

7) cbow_mean: 仅用于CBOW在做投影的时候，为0，则算法中的𝑥𝑤为上下文的词向量之和，为1则为上下文的词向量的平均值。在我们的原理篇中，是按照词向量的平均值来描述的。个人比较喜欢用平均值来表示𝑥𝑤,默认值也是1,不推荐修改默认值。

8) min_count:需要计算词向量的最小词频。这个值可以去掉一些很生僻的低频词，默认是5。如果是小语料，可以调低这个值。

9) iter: 随机梯度下降法中迭代的最大次数，默认是5。对于大语料，可以增大这个值。

10) alpha: 在随机梯度下降法中迭代的初始步长。算法原理篇中标记为𝜂，默认是0.025。

11) min_alpha: 由于算法支持在迭代的过程中逐渐减小步长，min_alpha给出了最小的迭代步长值。随机梯度下降中每轮的迭代步长可以由iter，alpha， min_alpha一起得出。这部分由于不是word2vec算法的核心内容，因此在原理篇我们没有提到。对于大语料，需要对alpha, min_alpha,iter一起调参，来选择合适的三个值。



## 2 预处理

```python
import os
from multiprocessing import Pool
import glob
import jieba
```

```python
# 加载停用词表
stop_words_file = open("data/stop_words.txt", 'r',encoding="utf-8",)
stop_words = list()
for line in stop_words_file.readlines():
    line = line.strip()   # 去掉每行末尾的换行符
    stop_words.append(line)
stop_words_file.close()
print(len(stop_words))
print(stop_words[300:320])
```

```python
def word_cut(file_path):
    file1, file2 = file_path
    file_w = open(file2, 'w+', encoding='utf-8')
    file_r = open(file1, 'r', encoding='utf-8')
    line = file_r.readline()
    line_count = 0
    while line:
        line_1 = line.strip()
        outstr = ''
        line_seg = jieba.cut(line_1, cut_all=False)
        for word in line_seg:  
            if word not in stop_words:
                if word != '\t':
                    outstr += word 
                    outstr += " "
        outstr = str(outstr.strip())
        if len(outstr) != 0:
            file_w.writelines(outstr+"\n")
            line_count = line_count+1
        line = file_r.readline()
        if line_count>=5000: break
    file_r.close()
    file_w.close()
    print("{} finished，with {} Row".format(file1, str(line_count)))
    return 
```

```python
files = [i for i in os.listdir("./data/chatpeer_random_800w") if i.endswith(".txt")]
files_r_path = [os.path.join("./data/chatpeer_random_800w", i) for i in files]
files_w_path = [os.path.join("./data/chatpeer_random_800w_cut", "cut_"+i) for i in files]
files_path = list(zip(files_r_path, files_w_path))
```

```python
from utils import parallel_apply
tmp = parallel_apply(word_cut, files_path, 12, 1000)
```

```python
# 切分单词，过滤通用词
# pool = Pool(6)
# b = pool.map(f, files_path)
# pool.close()
# pool.join()
```

## 3 训练词向量

```python
from gensim.models import Word2Vec
from gensim.models import word2vec
import logging
```

```python
# 训练词向量

input_dir = "./data/chatpeer_random_800w_cut"

logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.INFO)

sentences = word2vec.PathLineSentences(input_dir)

model = Word2Vec(sentences,size=100, window=5, min_count=5,workers=16, iter=10)
```

```python
model.most_similar("好评", topn=10) # 余弦距离
```

```python
model.most_similar("差评", topn=10)
```

```python
## 保存词向量
model.wv.save_word2vec_format("model_vec.txt", fvocab=None, binary=False)
```

```python
len(model.wv.vocab.keys())
```

```python

```
