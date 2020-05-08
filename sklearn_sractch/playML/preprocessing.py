# -*- coding: utf-8 -*-
"""
Created on Mon Jan 21 19:32:40 2019

@author: dell
"""
import numpy as np


class StandarScaler:
    def __init__(self):
        self.mean_ = None
        self.std_ = None

    def fit(self,X):
        assert X.ndim == 2
        self.mean_ = np.mean(X,axis = 0)
        self.scale_ = np.std(X,axis = 0)
        return self

    def tranform(self,X):
        assert X.ndim == 2
        assert self.mean_ is not None and self.scale_ is not None
        assert X.shape[1] == len(self.mean_)
        resX = np.empty(shape=X.shape,dtype = float)
        for col in range(X.shape[1]):
            resX[:,col] = (X[:,col]-self.mean_(col))/self.scale_(col)
        return resX