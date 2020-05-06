package test76_minWindow;

import java.util.HashMap;

public class test01 {
    public static void main(String[] args) {
        String S = "";
        String T = "BA";
        String result = minWindow(S, T);
        System.out.println(result);

    }

    static public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int left = 0;
        int right = 0;
        int count = 0;
        String result = "";
        while (right < s.length()) {
            Character c = s.charAt(right);
            if (map.containsKey(c)) {
                if (map.get(c) > 0) count++;
                map.put(c, map.get(c) - 1);
            }
            while (count == t.length()) {
                c = s.charAt(left);
                String tmp = s.substring(left, right + 1);
                if (result.length() == 0) {
                    result = tmp;
                } else if (tmp.length() < result.length()) {
                    result = tmp;
                }
                if (map.containsKey(c)) {
                    if (map.get(c) >=0) count--;
                    map.put(c, map.get(c) + 1);
                }
                left++;
            }
            right++;
        }
        return result;
    }
}
