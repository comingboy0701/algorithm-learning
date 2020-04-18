package test03动态规划;

public class test02_knapsack {
    // 0,1背包问题
    public int knapsack(int W,int N,int[] wt,int[] val ){
        int[][] dp= new int[N+1][W+1];
        for (int i=1;i<=N;i++){
            for (int w=1;w<=W;w++){
                if (wt[i-1]>w){
                    dp[i][w] = dp[i-1][w];
                }else {
                    dp[i][w] = Math.max(dp[i-1][w-wt[i-1]]+val[i-1],dp[i-1][w]);
                }
            }
        }
        return dp[N][W];
    }
}
