# -*- coding: utf-8 -*-
"""
Created on Fri Jan 25 11:20:10 2019

@author: dell
"""

from sklearn.datasets  import fetch_mldata
import numpy as np 
from sklearn import datasets

A = np.random.random(100)
digist = datasets.load_digits()
X = digist.data
print(X)