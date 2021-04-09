# -*- coding: utf-8 -*-
"""
Created on Mon Jan 21 14:39:47 2019

@author: dell
"""

import numpy as np


def train_test_split(X, y, test_ratio=0.2, seed=None):
    assert X.shape[0] == y.shape[0]
    assert 0.0 <= test_ratio <= 1.0
    if seed:
        np.random.seed(seed)
    shuffle_indexes = np.random.permutation(len(X))
    test_size = int(len(X)*test_ratio)
    test_indexes = shuffle_indexes[:test_size]
    train_inexes = shuffle_indexes[test_size:]
    X_train = X[train_inexes]
    y_train = y[train_inexes]
    X_test = X[test_indexes]
    y_test = y[test_indexes]
    return X_train, X_test, y_train, y_test