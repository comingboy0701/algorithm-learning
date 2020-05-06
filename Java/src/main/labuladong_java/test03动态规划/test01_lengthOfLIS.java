package test03动态规划;

import java.util.Arrays;

public class test01_lengthOfLIS {
    // 最长递增子序列串。。。。(字串和子序列串的区别)
    public int lengthOfLIS(int[] nums){
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for(int j=0;j<i;j++){
                if (nums[i]<=nums[j]) continue;
                dp[i] = Math.max(dp[i],dp[j]+1);
            }
        }
        int max =1;
        for (int i : dp) {
            if (i>max) max=i;
        }
        return max;
    }
}
