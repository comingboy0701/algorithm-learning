package test116_connect;


//import java.util.LinkedList;
//import java.util.Queue;

import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
//        Queue<Integer> q = new LinkedList<>(){};
//        for (int i = 0; i <10 ; i++) {
//            q.add(i);
//        }
//        System.out.println(q);
//        int a  = q.poll();
//        System.out.println("a:"+a);
//        System.out.println(q);
//        int b = q.peek();
//        System.out.println("b:"+b);
//        System.out.println(q);

        Stack<Integer> q = new Stack<Integer>();

        for (int i = 0; i <10 ; i++) {
            q.add(i);
        }
        System.out.println(q);
        int a  = q.pop();
        System.out.println("a:"+a);
        System.out.println(q);
        int b = q.peek();
        System.out.println("b:"+b);
        System.out.println(q);

    }
}
