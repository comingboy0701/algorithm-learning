package test361_MyQueue;

public class test01 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        System.out.println(myQueue.empty());
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.empty());
        int res = myQueue.pop();
        System.out.println(res);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }
}
