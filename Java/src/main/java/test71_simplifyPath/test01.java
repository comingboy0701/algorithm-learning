package test71_simplifyPath;

import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        String path ="/a/../../b/../c//.//";
        String result = simplifyPath(path);
        System.out.println(result);

    }
    static  public String simplifyPath(String path) {
        String[] list = path.split("/");
        Stack<String> stack = new  Stack<>();
        for (int i = 0; i < list.length; i++) {
            if (!stack.isEmpty()&&list[i].equals("..")){
                stack.pop();
            }else if(!list[i].equals("")&&!list[i].equals(" ")&&!list[i].equals(".")&&!list[i].equals("..")){
                stack.push(list[i]);
            }
        }
        if (stack.isEmpty()) return "/";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            str.append("/"+stack.pop());
        }
        return str.toString();
    }
}
