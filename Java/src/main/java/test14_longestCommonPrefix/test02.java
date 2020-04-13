package test14_longestCommonPrefix;

public class test02 {
    public static void main(String[] args) {
        String[] strs = {"abc","abc","abd"};
        System.out.println("abc".indexOf("abed"));
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }
    public static String longestCommonPrefix(String[] strs){
        if (strs.length==0||strs==null) return "";
        String str = strs[0];
        for (int i = 0; i < strs.length; i++) {
            while (strs[i].indexOf(str)!=0){
                str = str.substring(0,str.length()-1);
            }
        }
        return str;
    };
}
