package test189_rotate;

import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        new test01().rotate(nums,k);
        System.out.println(Arrays.toString(nums));

    }
    public void rotate(int[] nums,int k){
        int len = nums.length;
        k = k%len;
        reverse(nums,0,len-1);
        reverse(nums,0,k-1);
        reverse(nums,len-k-1,len-1);
    }

    private void reverse(int[] nums,int start,int end){
        while (start<end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = nums[start];
        }
    }

}
