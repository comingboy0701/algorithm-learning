package test150_evalRPN;

import java.time.Instant;
import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        String[] tokens = {"4","13","5","/","+"};
        int res = evalRPN(tokens);
        System.out.println(res);
    }

    static public int evalRPN(String[] tokens) {
        // 栈实现
        Stack<Integer> stack = new Stack<>();
        int num1, num2;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num1 + num2);
                    break;
                case "-":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2- num1);
                    break;
                case "*":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num1 * num2);
                    break;
                case "/":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 / num1);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
