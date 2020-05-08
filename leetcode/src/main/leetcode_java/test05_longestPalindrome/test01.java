package test05_longestPalindrome;

public class test01
{
    public static void main(String[] args) {
        String s = "abcabcb";
        System.out.println(longestPalindrome(s));
    }
    public static boolean isPalindromic(String s){
        int len = s.length();
        for (int i = 0; i <len/2 ; i++) {
            if (s.charAt(i)!=s.charAt(len-i-1)) return false;
        }
        return true;
    }
    public static String longestPalindrome(String s){
        String  ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j <=len ; j++) {
                String test = s.substring(i,j);
                if (isPalindromic(test) && test.length()>max) {
                    ans = s.substring(i,j);
                    max = Math.max(ans.length(),max);
                }
            }
            
        }
        return ans;
    }
}
