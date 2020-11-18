# -*- coding: utf-8 -*-
# ## 元组拆包

divmod(20, 8)

t = (20, 8)

divmod(*t)

import os
_, filename = os.path.split('/home/luciano/.ssh/idrsa.pub') 
filename

a, b, *rest = range(5)

a , b, rest

a, b, *rest = range(2)

a , b, rest

a, *body, c, d = range(5)

a, body, c, d 

from collections import namedtuple

City = namedtuple('City', 'name country population coordinates')

tokyo = City('Tokyo', 'JP', 36.933, (35.689722, 139.691667))

tokyo

tokyo.population

tokyo.coordinates

LatLong = namedtuple('LatLong', 'lat long')
delhi_data = ('Delhi NCR', 'IN', 21.935, LatLong(28.613889, 77.208889))

delhi_data

delhi = City._make(delhi_data)

delhi

delhi._asdict()


