package test179_largestNumber;

import java.util.Arrays;
import java.util.Comparator;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {10,2,34,2};
        String result = new test01().largestNumber(nums);
        System.out.println(result);
    }

    public String largestNumber(int[] nums){
        Integer[] n = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        Arrays.sort(n, (o1,o2)-> {
                String s1 = o1+""+o2;
                String s2 = o2+""+o1;
                return s2.compareTo(s1);
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            result.append(n[i]);
        }

        return result.toString().charAt(0)=='0'?"0":result.toString();
    }
}
