package test44_isMatch;

public class test01 {
    public static void main(String[] args) {
        String s = "cb";
        String p = "?a";
        Boolean result = isMatch(s,p);
        System.out.println(result);

    }

    static  public Boolean isMatch(String s,String p){
        if (s==null && p==null) return true;
        Boolean[][] dp = new Boolean[s.length()+1][p.length()+1];
        dp[0][0] =true;
        // s 为空字符串
        for (int i = 1;i<=p.length();i++){
            dp[0][i] = p.charAt(i-1)=='*'&&dp[0][i-1];
        }
        // 有点奇怪的算法
//        for (int i = 2;i<=p.length();i++){
//            dp[0][i] = p.charAt(i-1)=='*'&&dp[0][i-2];
//        }

        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i <= s.length() ; i++) {
            for (int j = 1; j <= p.length() ; j++) {
                if (s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i-1][j]||dp[i][j-1];
                }
                else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
