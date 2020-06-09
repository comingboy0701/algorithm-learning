package test191_hammingWeight;

public class test01 {
    public static void main(String[] args) {
        int n =00000000000000000000000000001011;
        int res = new test01().hammingWeight(n);
        System.out.println(res);
    }
    public int hammingWeight(int n) {
        int sum = 0;
        while(n!=0){
            n = n&(n-1);
            sum++;
        }
        return sum;
    }

}
