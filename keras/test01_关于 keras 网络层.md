```python
from keras.layers import Dense
```

```python
layer = Dense(32)
config = layer.get_config()
reconstructed_layer = Dense.from_config(config)
```

```python
config
```

```python
reconstructed_layer
```

```python
layer
```

```python

```
