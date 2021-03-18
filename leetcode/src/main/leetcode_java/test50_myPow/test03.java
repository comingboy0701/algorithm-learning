package test50_myPow;

public class test03 {
    public static void main(String[] args) {
        double x = 2;
        int n = -2;
        double res = new test03().myPow(x, n);
        System.out.println(res);

    }

    public double myPow(double x, int n) {
        if (n==0) return 1;
        return n>0?quickMul(x, n) : 1/quickMul(x, n);
    }


    public double quickMul(double x, long N){
        if (N==0) return 1;
        double res = quickMul(x, N/2);
        return N%2==0 ? res*res : res*res*x;
    }
}
