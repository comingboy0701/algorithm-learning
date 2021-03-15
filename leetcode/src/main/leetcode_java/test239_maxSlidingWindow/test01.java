package test239_maxSlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class test01 {
    public static void main(String[] args) {
        int[] num = {1,2,3,4,5,1,2,3};
        int[] res = new test01().maxSlidingWindow(num, 3);
        System.out.println(Arrays.toString(res));


    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i-deque.peekFirst()>=k){
                deque.pollFirst();
            }
            if(i >= k - 1){
                res[i + 1 - k] = nums[deque.peekFirst()];
            }
        }
    return res;
    }
}
