package test416_canPartition;


public class test01 {

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        boolean res = new test01().canPartition(nums);
        System.out.println(res);
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];

        if (nums[0]<target){
            dp[0][nums[0]] = true;
        }
        dp[0][0]  = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    dp[i][j] =  dp[i - 1][j]||dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
