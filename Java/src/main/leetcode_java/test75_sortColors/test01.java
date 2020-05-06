package test75_sortColors;


import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    static public void sortColors(int[] nums) {
        int[] array = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            array[nums[i]] = array[nums[i]] + 1;
        }
        int k =0;
        for (int i = 0; i < array.length; i++) {
            int count = array[i];
            while (count>0){
                nums[k] = i;
                count--;
                k++;
            }
        }
    }
}
