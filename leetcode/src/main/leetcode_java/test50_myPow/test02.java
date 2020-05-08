package test50_myPow;

public class test02 {
    public static void main(String[] args) {
        double x = 2;
        int n = 5;
        double result = myPow(x, n);
        System.out.println(result);
    }

    static public double myPow(double x, int n) {
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
