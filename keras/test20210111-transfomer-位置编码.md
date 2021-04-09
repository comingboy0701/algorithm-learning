```python
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
```

```python
def get_positional_encoding(max_seq_len, embed_dim):
  # 初始化一个positional encoding
  # embed_dim: 字嵌入的维度
  # max_seq_len: 最大的序列长度
  positional_encoding = np.array([
      [np.sin(pos / np.power(10000, 2 * i / embed_dim)) if i%2==0 else 
       np.cos(pos / np.power(10000, 2*i/embed_dim))
       for i in range(embed_dim) ]
       for pos in range(max_seq_len)])
 
  return positional_encoding


positional_encoding = get_positional_encoding(max_seq_len=100, embed_dim=16)


plt.figure(figsize=(16,6))
plt.subplot(1,2,1)
sns.heatmap(positional_encoding)
plt.title("Sinusoidal Function")
plt.xlabel("hidden dimension")
plt.ylabel("sequence length")

plt.subplot(1,2,2)
plt.plot(positional_encoding[:, 1], label="dimension 1")
plt.plot(positional_encoding[:, 2], label="dimension 2")
plt.plot(positional_encoding[:, 3], label="dimension 3")
plt.legend()
plt.xlabel("Sequence length")
plt.ylabel("Period of Positional Encoding")
```

```python
positional_encoding[1:,1].shape
```

```python

```
