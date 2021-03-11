package test1052_maxSatisfied;

public class test {
    public static void main(String[] args) {

    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i]!=0){
                res = res+customers[i];
            }
        }
        int max = 0, cur = 0;
        for (int i = 0, j = 0; i < grumpy.length; i++) {
            cur += customers[i];
            if (i - j + 1 > X) cur -= customers[j++];
            max = Math.max(max, cur);

        }
        return max+res;
    }
}
