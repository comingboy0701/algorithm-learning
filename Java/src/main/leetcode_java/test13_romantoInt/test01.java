package test13_romantoInt;

import java.util.HashMap;
import java.util.Map;

public class test01 {
    public static void main(String[] args) {
        String str = "IV";
        int result = romantoInt(str);
        System.out.println(result);
    }


    public static int romantoInt(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        int index = 0;
        int num = 0;
        while (index<s.length()){
            if (index+1<s.length()&&map.containsKey(s.substring(index,index+2))){
                num = num+map.get(s.substring(index,index+2));
                index = index+2;
            }
            else {
                num = num+map.get(s.substring(index,index+1));
                index = index+1;
            }
        }
        return num;
    }
}
