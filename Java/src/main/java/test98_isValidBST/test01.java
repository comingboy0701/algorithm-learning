package test98_isValidBST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        Queue<Integer> q= new LinkedList();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <10 ; i++) {
            q.add(i);
            stack.push(i);
        }
        while (!q.isEmpty()){
            System.out.print(q.remove()+",");
        }
        System.out.println("");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+",");
        }
    }


}


