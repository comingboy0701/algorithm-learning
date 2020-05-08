package test151_reverseWords;


public class test01 {
    public static void main(String[] args) {
        String s = "a good   example";
        String res = reverseWords(s);
        System.out.println(res);
    }

    static public String reverseWords(String s) {
     String[] list = s.split(" ");
     StringBuilder buffer = new StringBuilder();
        for (int i = list.length-1; i >= 0; i--) {
            if (!list[i].equals("")){
                buffer.append(list[i]);
                buffer.append(" ");
            }
        }
    return buffer.toString().trim();
    }
}
