package test58_lengthOfLastWord;

public class test01 {
    public static void main(String[] args) {
        String s = "faf faf  ";
        int result = lengthOfLastWord(s);
        System.out.println(result);
    }
//    static public int lengthOfLastWord(String s) {
//        s = s.trim();
//        String[] list = s.split(" ");
//        int len = list[list.length-1].length();
//        return len;
//    }

    static public int lengthOfLastWord(String s) {
        int n = 0;
        int len = s.length();
        while(len>0){
            len--;
            if(s.charAt(len)!=' '){
                n = n+1;
            }else if(s.charAt(len)==' '& n>0){
                return n;
            }
        }
        return n;
    }
}
