package test03动态规划;

import java.util.Arrays;

import static java.lang.Integer.min;

public class test06_coinChange{
    public int coinChange(int[] coins, int amount) {
        // 数组大小为 amount + 1，初始值也为 amount + 1
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 在求所有子问题 + 1 的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) continue;
                dp[i] = min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
