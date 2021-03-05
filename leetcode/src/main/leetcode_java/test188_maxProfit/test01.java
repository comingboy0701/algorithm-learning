package test188_maxProfit;

public class test01 {
    public static void main(String[] args) {
        int k = 2;
        int[] prices = {};
        int res = new test01().maxProfit(k, prices);
        System.out.println(res);
    }

    public int maxProfit(int max_k, int[] prices) {
        int n = prices.length;
        if (n==0) return 0;
        if (max_k > n/2){
            return maxProfit_k_inf(prices);
        }
        int[][][] dp = new int[n][max_k+1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k > 0; k--) {
                if(i-1==-1){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1]+prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0]-prices[i]);
            }
        }

        return  dp[n-1][max_k][0];
    }

    public int maxProfit_k_inf(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i-1==-1){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);

        }
        return dp[n-1][0];
    }
}
