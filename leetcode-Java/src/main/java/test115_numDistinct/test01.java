package test115_numDistinct;

public class test01 {
    public static void main(String[] args) {
        String S = "rabbbit";
        String T = "rabbit";
        int result  = numDistinct(S,T);
        System.out.println(result);
    }
    static public int numDistinct(String S,String T){
        int len1 = S.length();
        int len2 = T.length();
        int[][] dp = new int[len2+1][len1+1];
        for(int j=0; j<=len1; ++j) dp[0][j] = 1;

        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <=len1; j++) {
                dp[i][j] = dp[i][j-1]  ;
                if(S.charAt(j-1)==T.charAt(i-1)) dp[i][j] = dp[i][j]+dp[i-1][j-1];
            }

        }
        return dp[len2][len1];
    }

}
