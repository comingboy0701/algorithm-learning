package test20_isValid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class test01 {
    public static void main(String[] args) {
        String str = "";
        Boolean result = isValid(str);
        System.out.println(result);

    }

    static Map<String, String> map = new HashMap() {{
        put("}", "{");
        put(")", "(");
        put("]", "[");
    }};

    public static Boolean isValid(String str) {
        if (str == null || str.length() < 1) return true;
        int len = str.length();
        LinkedList list = new LinkedList<>();
        String end = "";
        for (int i = 0; i < len; i++) {
            String start = str.substring(i, i + 1);
            if (map.containsKey(start) && map.get(start).equals(end)) {
                list.removeLast();
                if (list.isEmpty()) {
                    end = "";
                } else {
                    end = (String) list.getLast();
                }
            } else {
                list.add(start);
                end = start;
            }
        }
        if (list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
