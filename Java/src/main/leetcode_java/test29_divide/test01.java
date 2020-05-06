package test29_divide;

public class test01 {
    public static void main(String[] args) {
        int dividend = - 10;
        int divisor = -2;
        int result = divide(dividend,divisor);
        int x = 3;
        System.out.println((x%2)==1); // 判断是否是偶数;
        System.out.println((10&2));
        System.out.println(result);

    }
    static public int divide(int dividend, int divisor){
        boolean sign = (dividend > 0) ^ (divisor > 0);
        if (dividend>0) dividend = -dividend;
        if (divisor>0) divisor = -divisor;
        int result = 0;
        while (dividend<=divisor){
            int temp_result = -1;
            int temp_divisor = divisor;
            while (dividend<=(temp_divisor<<1)){
                if (temp_divisor<=(Integer.MIN_VALUE>>1)) break;
                temp_divisor = temp_divisor<<1;
                temp_result = temp_result<<1;
            }
            result = result+temp_result;
            dividend = dividend-temp_divisor;
        }
        if (!sign){
            if (result<=(Integer.MIN_VALUE)) return Integer.MAX_VALUE;
            result = -result;
        }
        return  result;
    }


}
