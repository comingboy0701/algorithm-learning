package test91_numDecodings;

public class test01 {
    public static void main(String[] args) {
        String s = "226";
        int result = numDecodings(s);
        System.out.println(result);
    }
    static public int numDecodings(String s){
        int len = s.length();
        if (s.charAt(0)==0) return 0;
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < len; i++) {
            int num = (s.charAt(i-1)-'0')*10+(s.charAt(i)-'0');
            if (s.charAt(i)!='0') dp[i+1] = dp[i];
            if (num>9&&num<=26) dp[i+1] = dp[i+1]+dp[i-1];
            if (dp[i+1]==0) return 0;
        }
        return dp[len];
    }

}
