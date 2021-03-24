package test300_lengthOfLIS;

public class test01 {
    public static void main(String[] args) {
        int [] nums = {10,9,2,5,3,7,101,18, 200,201, 19};
        int res = new test01().lengthOfLIS(nums);
        System.out.println(res);
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new  int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[j]<nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int res = 0;
        for (int i : dp) {
            if(i>res){
                res=i;
            }
        }
        return res;
    }
}
