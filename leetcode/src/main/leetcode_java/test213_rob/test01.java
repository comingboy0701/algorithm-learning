package test213_rob;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,1};
        int res = new test01().rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        //可以偷第一家，但不能偷最后一家
        int robFirst = robHelper(nums, 0, nums.length - 2);
        //可以偷最后一家，但不能偷第一家
        int robLast = robHelper(nums, 1, nums.length - 1);
        //选择偷第1家和不偷第1家结果的最大值
        return Math.max(robFirst, robLast);
    }

    private int robHelper(int[] nums, int start, int end) {
        int[][] dp = new int[end+1][2];
        dp[0][0] = 0;
        dp[0][1] = nums[start];
        for (int j = start+1; j <= end; j++) {
            dp[j][0] = Math.max(dp[j - 1][0], dp[j - 1][1]);
            dp[j][1] = dp[j - 1][0] + nums[j];
        }
        return  Math.max(dp[end][0], dp[end][1]);
    }
}
