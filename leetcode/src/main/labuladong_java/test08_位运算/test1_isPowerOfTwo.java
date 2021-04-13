package test08_位运算;

public class test1_isPowerOfTwo {
    public static void main(String[] args) {
        int n =1;
        boolean res = new test1_isPowerOfTwo().isPowerOfTwo(n);
        System.out.println(res);

    }

    public boolean isPowerOfTwo(int n) {
        if (n<=0) return false;
        return (n&(n-1))==0;
    }
}
