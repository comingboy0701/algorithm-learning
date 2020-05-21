package test168;


import java.util.HashMap;

public class test01 {
    public static void main(String[] args) {
        String res = new test01().convertToTitle(27);
        System.out.println(res);
    }

    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            n -= 1;
            int c = n % 26;
            res.insert(0, (char) ('A' + c));
            n /= 26;
        }
        return res.toString();
    }
}
