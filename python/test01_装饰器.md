## 1 装饰器-简单使用

```python
import time

# 定义装饰器
def time_calc(func):
    def wrapper(*args, **kargs):        
        start_time = time.time()        
        f = func(*args,**kargs)        
        exec_time = time.time() - start_time 
        print("'%s' function exec time:%s sec" %(func.__name__ ,str(exec_time)))
        return f    
    return wrapper   
    
# 使用装饰器
@time_calc    
def add(a, b):
    return a + b
    
@time_calc
def sub(a, b):    
    return a - b
```

```python
add(1, 1)
```

```python
sub(1,2)
```

## 2 带参数的装饰器

```python
import logging 
    
def use_logging(level):
    def decorator(func):
        def wrapper(*args, **kwargs):
            if level == "warn":
                logging.warning("%s is running" % func.__name__)
            return func(*args)
        return wrapper
    return decorator
```

```python
@use_logging(level="warn")
def foo(name='foo'):
    print("i am %s" % name)
```

```python
foo()
```

```python

```
