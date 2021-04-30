from math import hypot


class Vector:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __repr__(self):
        return 'Vector(%r, %r)' % (self.x, self.y)

    def __abs__(self):
        return hypot(self.x, self.y)

    def __bool__(self):
        return bool(abs(self))

    def __add__(self, other):
        x = self.x + other.x
        y = self.y + other.y
        return Vector(x, y)

    def __mul__(self, scalar):
        return Vector(self.x * scalar, self.y * scalar)
    
    def __eq__(self,x):
        return self.x==x.x and self.y==x.y


vector_01 = Vector(3,4)
vector_02 = Vector(3,4)
vector_03 = Vector(6,8)

print(vector_01,vector_02)

abs(vector_01)

vector_01*3

vector_01+vector_02

vector_03 = Vector(0,0)
bool(vector_03)

bool(vector_01)

vector_01==vector_03


