# -*- coding: utf-8 -*-
"""
Created on Mon Jan 21 15:32:53 2019

@author: dell
"""
from math import sqrt
import numpy as np


def accracy_score(y_true, y_predict):
    assert y_true.shape[0] == y_predict.shape[0]
    return sum(y_predict==y_true)/len(y_true)


def mean_squared_error(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return sum((y_true-y_predict)**2)/len(y_true)


def root_mean_squared_error(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return sqrt(sum((y_true-y_predict)**2)/len(y_true))


def mean_absolute_error(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return sum(np.absolute(y_true-y_predict))/len(y_true)


def r2_score(y_true, y_predict):
    return 1 - mean_squared_error(y_true, y_predict)/np.var(y_true)


def TN(y_true,y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true==0) & (y_predict==0))


def FP(y_true,y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true==0) & (y_predict==1))

def FN(y_true,y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true==1) & (y_predict==0))

def TP(y_true,y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true==1) & (y_predict==1))

def confusion_matrix(y_true,predict):
    return np.array([
        [TN(y_true,predict),FP(y_true,predict)],
        [FN(y_true,predict),TP(y_true,predict)]
        ])

def presion_score(y_true,predict):
    tp = TP(y_true,predict)
    fp = FP(y_true,predict)
    try :
        return tp/(tp+fp)
    except:
        return


def recall_score(y_true,predict):
    tp = TP(y_true,predict)
    fn = FN(y_true,predict)
    try :
        return tp/(tp+fn)
    except:
        return


def f1_score(prescision,recall):
    try:
        return 2*(prescision*recall)/(prescision+recall)
    except:
        return

def TPR(y_true,y_predict):
    tp = TP(y_true,y_predict)
    fn = FN(y_true,y_predict)
    try:
        return tp/(tp+fn)
    except:
        return 0


def FPR(y_true,y_predict):
    fp = FP(y_true,y_predict)
    tn = TN(y_true,y_predict)
    try:
        return fp/(fp+tn)
    except:
        return 0