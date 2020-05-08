package test38_CountAndSay;

public class test01 {
    public static void main(String[] args) {
        int n = 10;
        String result = countAndSay(n);
        System.out.println(result);

    }
    static public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            char pre = str.charAt(0);
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                char c = str.charAt(j);
                if (c == pre) {
                    count++;
                } else {
                    builder.append(count).append(pre);
                    pre = c;
                    count = 1;
                }
            }
            builder.append(count).append(pre);
            str = builder.toString();
        }

        return str;
    }

}
