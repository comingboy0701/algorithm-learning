```python
arr = [
    [0,1,3,4],
    [0,1,3,4],
    [0,1,3,4],
    [0,1,3,4],
]
```

```python
for k in range(len(arr)-1, -1, -1):
    tmp = []
    i = k
    j = k
    while(i<=len(arr)-1):
        tmp.append(arr[i][j])
        i = i+1
        j = j+1
    print(tmp)
    

for k in range(1, len(arr)):
    tmp = []
    i = k
    j = 0
    while(i<=len(arr)-1):
        tmp.append(arr[i][j])
        i = i+1
        j = j+1
    print(tmp)
```

```
[4]
[3, 4]
[1, 3, 4]
[0, 1, 3, 4]
[0, 1, 3]
[0, 1]
[0]
```
