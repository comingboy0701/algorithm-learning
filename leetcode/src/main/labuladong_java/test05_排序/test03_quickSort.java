package test05_排序;

import java.util.Arrays;

public class test03_quickSort {

    public static void main(String[] args) {
        int[] nums = {1,2, 12,3,3,1,1, 45,34,4};
        new test03_quickSort().sort(nums);
        System.out.println(Arrays.toString(nums));

    }

    public void sort(int[] nums){
        quickSort(nums, 0 ,nums.length-1);
    }

    private void quickSort(int[] nums, int lo, int hi){
        if (lo>=hi) return;
        int p = partition(nums, lo, hi);
        quickSort(nums, lo, p-1);
        quickSort(nums, p+1, hi);
    }


    private int partition(int[] nums, int left, int right){
        if (left==right) return left;
        int start = left;
        while (left<right){
            while (left<right&&nums[left]<=nums[start]){
                left++;
            }
            while (left<right&&nums[right]>=nums[start]){
                right--;
            }
            if (left<right){
                swap(nums, left, right);
            }
        }
        swap(nums, right, start);

        return right;

    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
