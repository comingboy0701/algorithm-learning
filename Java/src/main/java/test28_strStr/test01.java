package test28_strStr;

public class test01 {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "lle";
        int result = strStr(haystack,needle);
        System.out.println(result);
    }
    static public int strStr(String haystack,String needle){
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len1<len2) return -1;
        int result = -1;
        for (int i = 0; i <len1 ; i++) {
            result = i;
            for (int j = 0; j <len2 ; j++) {
                if (haystack.charAt(i+j)!=needle.charAt(j)){
                    result =-1;
                    break;
                }
            }
            if (result>-1){
                return result;
            }
        }
        return result;
    }
}
