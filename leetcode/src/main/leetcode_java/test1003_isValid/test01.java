package test1003_isValid;

import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        String s = "abc";
        Boolean res = new test01().isValid(s);
        System.out.println(res);

    }
    public boolean isValid(String s){
       Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c=='a'){
                stack.push(c);
            }
            else if(c=='b'){
                if (stack.empty()) return false;
                else stack.push(c);
            }
            else {
                if (stack.size()<2) return false;
                char first = stack.pop();
                char secondary = stack.pop();
                if (first != 'b' || secondary != 'a') return false;
            }
        }
        return stack.isEmpty();
    }
}
