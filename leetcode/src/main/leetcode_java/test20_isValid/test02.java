package test20_isValid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class test02 {
    public static void main(String[] args) {
        String str = "[])";
        Boolean result = isValid(str);
        System.out.println(result);
    }
    static Map<Character, Character> map = new HashMap() {{
        put('{', '}');
        put('(', ')');
        put('[',']');
        put('*','*');
    }};
    public static boolean isValid(String str){
        LinkedList list = new LinkedList() {{ add('*'); }};
        if (str.length()>0 && !map.containsKey(str.charAt(0))) return false;
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                list.addLast(c);
            }else if(map.get(list.removeLast())!=c){
                return false;
            }
        }
        return list.size() == 1;
    }
}
