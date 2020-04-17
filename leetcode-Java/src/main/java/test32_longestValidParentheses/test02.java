package test32_longestValidParentheses;

import java.util.Stack;

public class test02 {
    public static void main(String[] args) {
        String s = "))))";
        int result = longestValidParentheses(s);
        System.out.println();
        System.out.println(result);
    }

    static public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        for (int i = 0; i <s.length() ; i++) {
            if (s.charAt(i)=='('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else{
                    maxans = Math.max(maxans,i-stack.peek());
                }
            }

        }
        return maxans;
    }
}

