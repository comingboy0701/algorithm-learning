package test14_longestCommonPrefix;

public class test01 {
    public static void main(String[] args) {
        String[] strs = {"abc","eabc","eabd"};
        System.out.println("abc".indexOf("abed"));
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }
    public static String longestCommonPrefix(String[] strs){
        if (strs.length==0||strs==null) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i==strs[j].length()||c!=strs[j].charAt(i)){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    };
}
