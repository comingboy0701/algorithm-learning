# -*- coding: utf-8 -*-
# ## 序列的增量赋值

# ### 可变序列

l = [1,2,3]
print(l, id(l))
l *=2
print(l, id(l))
l +=[1]
print(l, id(l))

# ### 不可变序列

l = (1,2,3)
print(l, id(l))
l *=2
print(l, id(l))

import dis

dis.dis('s[a] += b')
