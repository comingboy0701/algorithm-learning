```python
def findDiagonalOrder(mat):
    res = []
    for k in range(0, len(mat[0])):
        i = 0
        j = k
        tmp = []
        while (i<len(mat) and j>=0):
            tmp.append(mat[i][j])
            i = i+1
            j = j-1
        res.append(tmp)
        
    for k in range(1, len(mat)):
        i = k
        j = len(mat[0])-1
        tmp = []
        while (i<len(mat) and j>=0):
            tmp.append(mat[i][j])
            i = i+1
            j = j-1
        res.append(tmp)
    for i in range(2, len(res), 2):
        res[i] = res[i][::-1]
    res = [j for i in res for j in i] 
    return res
```

```python
arr = [
    [0,1,3,4],
    [0,1,3,4],
]
```

```python
findDiagonalOrder(arr)
```

```python

```
