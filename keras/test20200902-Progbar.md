```python
import time
from keras.utils import Progbar

pbar = Progbar(100, stateful_metrics=['something'])
for i in range(100):
    pbar.update(i + 1, values=[('something', i - 10)])
    time.sleep(0.1)
```

```python

```
