package test08_位运算;

public class test191_hammingWeight {
    public static void main(String[] args) {
        int n =3;
        int res = new test191_hammingWeight().hammingWeight(n);
        System.out.println(res);

    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n!=0){
            n = n&(n-1);
            res++;
        }
        return res;
    }
}
