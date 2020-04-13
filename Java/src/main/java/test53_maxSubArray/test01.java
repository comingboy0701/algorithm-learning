package test53_maxSubArray;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int result = maxSubArray(nums);
        System.out.println(result);
    }

    static  public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i],nums[i]+curr);
            max = Math.max(max,curr);
        }
        return max;
    }
}
