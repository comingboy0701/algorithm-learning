# -*- coding: utf-8 -*-
"""
Created on Wed Jan 23 14:13:22 2019

@author: dell
"""
import numpy as np
from .metrices import accracy_score

class LogisticRegression:
    def __init__(self):
        self.coef_ = None
        self.interception_ = None
        self._theta = None
    def _sigmod(self,t):
        return 1.0/(1.0+np.exp(-t)) 
    
    def predict_proba(self, X_predict):
        assert self.coef_ is not None and self.interception_ is not None
        assert X_predict.shape[1] == len(self.coef_)
        X_b = np.hstack([np.ones((len(X_predict), 1)), X_predict])
        return self._sigmod(X_b.dot(self._theta))
    
    def predict(self, X_predict):
        assert self.coef_ is not None and self.interception_ is not None
        assert X_predict.shape[1] == len(self.coef_)
        proba = self.predict_proba(X_predict)
        return np.array(proba>=0.5,dtype = 'int')

    def score(self, X_test, y_test):
        y_predict = self.predict(X_test)
        return accracy_score(y_test, y_predict)

    def fit(self, X_train, y_train, eta=0.01, n_iters=1e4):
        assert X_train.shape[0] == y_train.shape[0]

        def J(theta, X_b, y):  ## 计算损失函数
            y_hat = self._sigmod(X_b.dot(theta))
            try:
                return np.sum(y*np.log(y_hat)+(1-y)*np.log(1-y_hat)) / len(X_b)
            except:
                return float('inf')

        def dJ(theta, X_b, y):
            return X_b.T.dot(self._sigmod(X_b.dot(theta))-y)/len(X_b)

        def gradient_descent(X_b, y, initial_theta, eta, n_iters=1e4, epsilon=1e-8):
            theta = initial_theta
            i_iters = 0
            while i_iters < n_iters:
                gradient = dJ(theta, X_b, y)
                last_theta = theta
                theta = theta - eta * gradient
                if abs(J(theta, X_b, y) - J(last_theta, X_b, y)) < epsilon:
                    break
                i_iters += 1
            return theta

        X_b = np.hstack([np.ones([len(X_train), 1]), X_train])
        initial_theta = np.zeros(X_b.shape[1])
        self._theta = gradient_descent(X_b, y_train, initial_theta, eta, n_iters, epsilon=1e-8)
        self.interception_ = self._theta[0]
        self.coef_ = self._theta[1:]

        return self

    def __repr__(self):
        return " LogisticRegression()"
