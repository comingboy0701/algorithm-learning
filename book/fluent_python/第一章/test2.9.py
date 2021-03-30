# -*- coding: utf-8 -*-
# ### 数组

from array import array 
from random import random

floats = array('d', (random() for i in range(10))) 

floats[-1]

fp = open('floats.bin', 'wb')
floats.tofile(fp) 
fp.close()

floats2 = array('d') 
fp = open('floats.bin', 'rb')
floats2.fromfile(fp, 10) 
fp.close()

floats2[-1] 
floats2 == floats 

a = [1,"2",3,1,2]

# ### 内存视图




