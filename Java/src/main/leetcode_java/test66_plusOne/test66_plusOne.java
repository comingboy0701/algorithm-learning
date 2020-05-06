package test66_plusOne;



import java.util.Arrays;

public class test66_plusOne {
    public static void main(String[] args) {
        int[] digits = {0};
        int[] result = plusOne(digits);
        System.out.println(Arrays.toString(result));

    }
    static  public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n-1; i >= 0; i--) {
            digits[i] = digits[i]+1;
            digits[i] = digits[i]%10;
            if (digits[i]%10!=0) return digits;
        }
        int[] tmp = new int[n+1];
        tmp[0] = 1;
        return tmp;
    }


}

