package test31_nextPermutation;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,5,8,3,4};
        nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println(nums);
    }
    static public void nextPermutation(int[] nums){
        int i = nums.length-2;
        while (i>0 && nums[i]>nums[i+1]){
            i--;
        }
        if (i>=0){
            int j = nums.length-1;
            while (nums[i]>nums[j]){
                j--;
            }
            swap(nums,i,j);
            reverse(nums,i+1);
        }
//        return nums;
    }

    static public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
//        return nums;
    }

    static public void reverse(int[] nums,int j){
        int right = nums.length-1;
        while (j<right){
            swap(nums,j,right);
            j++;
            right--;
        }
//        return nums;
    }
}
