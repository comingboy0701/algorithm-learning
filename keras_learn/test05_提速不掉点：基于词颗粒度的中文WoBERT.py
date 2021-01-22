# -*- coding: utf-8 -*-
import numpy as np
from bert4keras.backend import keras, K
from bert4keras.layers import Loss
from bert4keras.tokenizers import Tokenizer
from bert4keras.models import build_transformer_model
from bert4keras.optimizers import Adam
from bert4keras.snippets import sequence_padding, DataGenerator
from bert4keras.snippets import open
from keras.layers import Lambda, Dense
import jieba

dict_path = '../../../bert_model/chinese_roberta_wwm_ext_L-12_H-768_A-12/vocab.txt'
tokenizer = Tokenizer(
    dict_path,
    do_lower_case=True,
    pre_tokenize=lambda s: jieba.cut(s, HMM=False)
)

tokenizer.encode("中国")

tokenizer.decode([101, 704, 1744, 102])

[tokenizer.id_to_token(i) for i in tokenizer.encode("中国")[0]]

jieba.cut("中国", HMM=False)


