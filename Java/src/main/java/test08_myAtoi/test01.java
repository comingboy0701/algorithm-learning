package test08_myAtoi;

public class test01 {
    public static void main(String[] args) {
        String s = "-131231";
        System.out.println(myAtoi(s));
    }
    public static int myAtoi(String str){
        if (str==null) return 0;
        str = str.trim();
        if (str.length()==0) return 0;
        int i=0;
        int flag =1;
        char ch = str.charAt(i);
        if (ch=='+'){
            i++;
        }else if(ch=='-'){
            flag = -1;
            i++;
        }
        int res =0;
        for (; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch < '0'||ch>'9'){
                break;
            }
            if (flag > 0 && res > Integer.MAX_VALUE / 10)
                return Integer.MAX_VALUE;
            if (flag > 0 && res == Integer.MAX_VALUE / 10 && ch - '0' > Integer.MAX_VALUE % 10)
                return Integer.MAX_VALUE;
            if (flag < 0 && -res < Integer.MIN_VALUE / 10)
                return Integer.MIN_VALUE;
            if (flag < 0 && -res == Integer.MIN_VALUE / 10 && -(ch - '0') < Integer.MIN_VALUE % 10)
                return Integer.MIN_VALUE;
            res = res * 10 + ch - '0';
        }
        return res*flag;
    }
}
