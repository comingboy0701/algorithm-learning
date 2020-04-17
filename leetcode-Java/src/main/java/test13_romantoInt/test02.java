package test13_romantoInt;

import java.util.HashMap;
import java.util.Map;

public class test02 {
    public static void main(String[] args) {
        String str = "IV";
        int result = romanToInt(str);
        System.out.println(result);
    }


    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        int sum = 0;
        int preNum = map.get(s.substring(0, 1));
        for(int i = 1;i < s.length(); i ++) {
            int num = map.get(s.substring(i, i+1));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }
}
