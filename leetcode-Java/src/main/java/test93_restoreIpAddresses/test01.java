package test93_restoreIpAddresses;


import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        String s = "0279245587303";
        List<String> list = restoreIpAddresses(s);
        System.out.println(list);
        

    }

    static public List<String> restoreIpAddresses(String s) {
        List<String> list = new LinkedList<>();
        StringBuilder ip = new StringBuilder();
        if (s.length()<=12) {
            for (int a = 1; a < 4; a++) {
                for (int b = 1; b < 4; b++) {
                    for (int c = 1; c < 4; c++) {
                        if (a + b + c < s.length()) {
                            int num1 = Integer.parseInt(s.substring(0, a));
                            int num2 = Integer.parseInt(s.substring(a, a + b));
                            int num3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int num4 = Integer.parseInt(s.substring(a + b + c));
                            if (num1 <= 255 && num2 <= 255 && num3 <= 255 && num4 <= 255) {
                                ip.append(num1).append(".").append(num2).append(".").append(num3).append(".").append(num4);
                                String tmp = ip.toString();
                                if (tmp.length() == s.length() + 3) {
                                    list.add(tmp);
                                }
                            }
                            ip.delete(0, ip.length());
                        }
                    }
                }
            }
        }
        return list;
    }
}
