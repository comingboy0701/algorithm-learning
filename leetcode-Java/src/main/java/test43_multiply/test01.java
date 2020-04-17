package test43_multiply;

public class test01 {
    public static void main(String[] args) {
        String num1 = "0";
        String num2 = "0";
        String result = multiply(num1,num2);
        System.out.println(result);
        System.out.println(Integer.parseInt(num1)*Integer.parseInt(num2));
    }
    static public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] array = new int[len1+len2];
        for (int i = len1-1; i >= 0; i--) {
            int n1 = num1.charAt(i)-'0';
            for (int j = len2-1; j >= 0; j--) {
                int n2 = num2.charAt(j)-'0';
                int sum  = array[i+j+1]+n1*n2;
                array[i+j+1] = sum%10;
                array[i+j] = array[i+j]+sum/10;
            }

        }
        StringBuilder str = new StringBuilder();
        for (int j = 0; j <array.length ; j++) {
            if(j==0&&array[j]==0) continue;
            str.append(array[j]);
        }
        return str.toString();
    }
}
