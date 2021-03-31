package test07_旋转数组;

import java.util.Arrays;

public class test189_rotate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        new test189_rotate().rotate(nums, 1);
        System.out.println(Arrays.toString(nums));


    }

    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums,  k, nums.length-1);
    }

    private void reverse(int[] num,int left,  int right){
        while (left<right){
            int tmp = num[left];
            num[left] = num[right];
            num[right] = tmp;
            left++;
            right--;
        }
    }
}
