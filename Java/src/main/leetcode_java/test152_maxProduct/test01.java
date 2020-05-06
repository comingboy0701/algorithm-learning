package test152_maxProduct;

public class test01 {
    public static void main(String[] args) {
        int[] num = {2,3,-2,4,2};
        int res = maxProduct(num);
        System.out.println(res);
    }
    static public int maxProduct(int[] num){
        int min = 1;
        int max = 1;
        int res = Integer.MIN_VALUE;
        for (int i : num) {
            int tmp_max = i*max;
            int tmp_min = i*min;
            max = Math.max(Math.max(tmp_max,i),tmp_min);
            min = Math.min(Math.min(tmp_min,i),tmp_max);
            res = Math.max(max,res);
        }
        return res;
    }
}
