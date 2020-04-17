package test34_searchRange;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,5};
        int[] result = searchRange(nums,4);
        System.out.println(Arrays.toString(result));
    }
    static public int[] searchRange(int[] nums,int target){
        int[] result = {-1,-1};
        if (nums.length==0) return result;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==target){
                result[0] = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i]==target){
                result[1] = i;
                break;
            }
        }
        return result;
    }
}
