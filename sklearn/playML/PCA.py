# -*- coding: utf-8 -*-
"""
Created on Thu Jan 24 16:44:22 2019

@author: dell
"""
import numpy as np
class PCA:
    def __init__(self,n_components):
        assert n_components>=1
        self.n_components = n_components
        self.components_ = None
    def fit(self,X,eta = 0.01,n_iter = 1e4):
        assert self.n_components <=X.shape[1]
        
        def demean(X): ## 归一均值归零花
            return X - np.mean(X,axis = 0)
        
        def f(w,X): ## 目标函数
            return np.sum((X.dot(w)**2.0))/len(X)
        
        def df(w,X): ## 目标函数的梯度（（数学计算的倒数）
            return X.T.dot(X.dot(w))*2.0/len(X)
        
        def dirction(x):
            return x / np.linalg.norm(x)
        
        def fisrt_compent(X,initial_w,eta, n_iters =1e4,epsilon = 1e-8):
            w = dirction(initial_w)
            i_iters =0
            while i_iters < n_iters:
                gradient = df(w,X)
                last_w = w
                w = w + eta*gradient
                w = dirction(w) # 归一成单位的向量
                if abs(f(w,X) - f(last_w,X)) < epsilon:
                    break
                i_iters +=1
            return w
        
        def first_n_compnents(n,X,eta = 0.001, n_iters =1e4,epsilon = 1e-8):
            X_pca = X.copy()
            X_pca = demean(X_pca)
            res =[]
            for i in range(n):
                initial_w = np.random.random(X_pca.shape[1])
                w = fisrt_compent(X_pca,initial_w,eta)
                res.append(w)
                X_pca = X_pca - X_pca.dot(w).reshape(-1,1)*w 
            return np.array(res) 
        self.components_ = first_n_compnents(self.n_components,X)
        return self
    
    def transform(self,X):
        assert X.shape[1] ==self.components_.shape[1]
        return X.dot(self.components_.T)
   
    
    def inverse_transform(self,X):
        assert X.shape[1]==self.components_.shape[0]
        return X.dot(self.components_)
    
    
    def __repr__(self):
        return "PCA(n_components=%d)"%self.n_components