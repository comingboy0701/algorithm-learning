package test67_addBinary;

public class test01 {
    public static void main(String[] args) {
        String a = "";
        String b = "";
        String result = addBinary(a,b);
        System.out.println(result);

    }
    static  public String addBinary(String a, String b) {
        int len_a = a.length()-1;
        int len_b = b.length()-1;
        StringBuilder str  = new StringBuilder();
        int ret = 0;
        while (len_a>=0 || len_b>=0){
            if (len_a>=0&len_b>=0){
                int a_i = a.charAt(len_a)-'0';
                int b_i = b. charAt(len_b)-'0';
                int tmp = a_i+b_i+ret;
                int mod = tmp%2;
                ret = tmp/2;
                str.append(String.valueOf(mod));
            }
            if (len_a>=0&len_b<0){
                int a_i = a.charAt(len_a)-'0';
                int tmp = a_i+ret;
                int mod = tmp%2;
                ret = tmp/2;
                str.append(String.valueOf(mod));
            }
            if (len_a<0&len_b>=0){
                int b_i = b.charAt(len_b)-'0';
                int tmp = b_i+ret;
                int mod = tmp%2;
                ret = tmp/2;
                str.append(String.valueOf(mod));
            }
            len_a =len_a-1;
            len_b = len_b-1;
        }
        if (ret>0) str.append(ret%2);
        return  str.reverse().toString();
    }
}
