package test87_isScramble;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ba";
        boolean result = isScramble(s1, s2);
        System.out.println(result);
    }

    static public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        if (!sortSting(s1).equals(sortSting(s2))) {
            return false;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
                return true;
        }
        return false;
    }

    static public String sortSting(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        String result = String.valueOf(array);
        return result;
    }
}
