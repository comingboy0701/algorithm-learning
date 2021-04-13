package test08_位运算;

public class test136_singleNumber {
    public static void main(String[] args) {
        int[] n ={1,2,2,1,4};
        int res = new test136_singleNumber().singleNumber(n);
        System.out.println(res);

    }

    public int singleNumber(int[] n) {
        int res = 0;
        for (int i : n) {
            res = res^i;
        }
        return res;
    }
}
