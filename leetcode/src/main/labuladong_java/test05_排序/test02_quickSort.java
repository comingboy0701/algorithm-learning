package test05_排序;

import java.util.Arrays;

public class test02_quickSort {

    public static void main(String[] args) {
        int[] nums = {1,2, 12,3,3,1,1, 45,34,4};
        new test02_quickSort().sort(nums);
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


    private int partition(int[] nums, int lo, int hi){
        if (lo==hi) return lo;
        int pivot = nums[lo];
        int i = lo;
        int j = hi+1;
        while (true){
            while (nums[++i]<pivot){
                if (i==hi) break;
            }
            while (nums[--j]>pivot){
                if (j==lo) break;
            }
            if (i>=j) break;
            swap(nums, i, j);
        }
        swap(nums, j, lo);

        return j;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
