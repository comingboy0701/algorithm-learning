package test128_longestConsecutive;

import java.util.HashSet;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        int res = longestConsecutive(nums);
        System.out.println(res);

    }
    static public int longestConsecutive(int[] nums) {
        HashSet set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (!set.contains(num-1)){
                int index = 1;
                num++;
                while (set.contains(num)){
                    index++;
                    num++;
                }
                res = Math.max(res,index);
            }
        }
        return res;
    }
}
