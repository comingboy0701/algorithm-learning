package test1052_maxSatisfied;

public class test {
    public static void main(String[] args) {
        int[] customers = {1,2,3,4};
        int[] grumpy = {1,0,0,0};
        int res = new test().maxSatisfied(customers, grumpy, 3);
        System.out.println(res);
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i]==0){
                res = res+customers[i];
            }
        }
        int max = 0, cur = 0;
        for (int i = 0, j = 0; i < grumpy.length; i++) {
            if (grumpy[i]==1){
                cur = cur+ customers[i];
            }
            if (i - j + 1 > X) {
                if (grumpy[j]==1){
                    cur = cur-customers[j];
                }
                j=j+1;
            };
            max = Math.max(max, cur);
        }
        return max+res;
    }
}
