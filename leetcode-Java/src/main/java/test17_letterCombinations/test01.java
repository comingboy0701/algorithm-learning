package test17_letterCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test01
{

    public static void main(String[] args) {
        String digits = "23";
        List<String> result = letterCombinations(digits);
        System.out.println(result);
    }

    static Map<String,String> phone = new HashMap(){{
        put("2","abc");
        put("3","def");
        put("4","ghi");
        put("5","jkl");
        put("6","mno");
        put("7","qprs");
        put("8","tuv");
        put("9","wxyz");
    }};
    static List<String> output =new ArrayList<>();
    public static void backtrack(String combination, String next_digits){
        if (next_digits.length()==0){
            output.add(combination);
        }else {
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }
    public static List<String> letterCombinations(String digits){

        if (digits.length()!=0){
            backtrack("",digits);
        }

        return output;
    };
}
