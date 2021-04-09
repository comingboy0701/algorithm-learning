# -*- coding: utf-8 -*-
"""
Created on Wed Jan 23 10:11:13 2019

@author: dell
"""
import numpy as np
from .metrices import r2_score


class SimpleLinearRegression:
    def __init__(self):
        self.a_ = None
        self.b_ = None

    def fit(self,x_train,y_train):
        assert x_train.ndim ==1
        assert len(y_train) == len(x_train)
        x_mean = np.mean(x_train)
        y_mean = np.mean(y_train)
        num = 0.0
        d = 0.0
        num = (x_train - x_mean).dot(y_train - y_mean)
        d = (x_train - x_mean).dot(x_train - x_mean)    
#        for x_i,y_i in zip (x_train,y_train):
#            num +=(x_i-x_mean)*(y_i-y_mean)
#            d +=(x_i-x_mean)**2
        self.a_ = num/d
        self.b_ = y_mean-self.a_*x_mean
        return self
    
    def predict(self,x_predict):
        assert x_predict.ndim == 1
        assert self.a_ is not None and self.b_ is not None
        return np.array([self._predict(x) for x in x_predict])

    def _predict(self,x_single):
        y_predict = self.a_*x_single+self.b_
        return y_predict
    
    def score(self,x_test,y_test):
        y_predict = self.predict(x_test)
        return r2_score(y_test,y_predict)
    
    def __repr__(self):
        return "SimpleLinearRegression()"