# -*- coding: utf-8 -*-
"""
Created on Mon Sep 30 08:41:37 2019

@author: dell
"""

def quicksort(arr,left,right):
    if right<=left:
        return
    low = left
    high = right
    ji = arr[low]
    while left <right:
        
        while left<right and arr[right]>ji:
            right-=1
        while left<right and arr[left]<=ji:
            left+=1
        arr[left],arr[right] = arr[right],arr[left]
        
    arr[right],arr[low] = arr[low], arr[right]
    quicksort(arr,low,left-1)
    quicksort(arr,left+1,high)

arr = [2,3,1,2,2,45,56,56,59,1,2,3,45,1555,23,1,2,5,4,56,45,12,0]
left = 0
right = len(arr)-1
quicksort(arr,left,right)
print(arr)


