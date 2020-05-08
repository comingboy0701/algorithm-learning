## 题目leetcode-50 
实现 pow(x, n) ，即计算 x 的 n 次幂函数。

## 1 递归详解

```Java
class Solution {
     public double myPow(double x, int n) {
         if (n==0) return 1;
         if (n==1) return x;
         if (n == -1) { return 1 / x; }
         double half = myPow(x, n / 2);
         double rest = myPow(x, n % 2);
         return rest * half * half;
     }
}
    
```

## 2 位运算

```Java
class Solution {
      public double myPow(double x, int n) {
         int result = 1;
         while (n != 0) {
             if ((n & 1) == 1) {
                 result *= x;
             }
             x *= x;
             n >>= 1;
         }
         return result;
     }
}

```

比如说3的11次方

11写成二进制就是1011

1011每一位应的权分别为23, 22, 21, 20, 即8, 4, 2, 1

如果某位为0,对应位的权重就没用了

所以3的11次方就是3^(8+2+1),即(3^8) * (3^2) * (3^1)

模拟

res =1

1011&1 ==1 res*(3^1)  1011 >>1 = 0101

0101&1==1 res*(3^2)  0101 >> 1== 0010

0010&1==0 res不用乘(3^4) 0010>>1==0001

0001&1==1 res*(3^8) 0001>>1 ==0

结束

x依次等于(3^1), (3^2) , (3^4) , (3^8)

x*x其实就是不断在更新x的值

但是无论res乘还是不乘x，都需要更新x

