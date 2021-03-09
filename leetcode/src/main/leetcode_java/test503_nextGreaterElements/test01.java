package test503_nextGreaterElements;

import java.util.Arrays;
import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        int[] num = {1,2,1};
        int[] res = new test01().nextGreaterElements(num);
        System.out.println(Arrays.toString(res));
    }

    public int[] nextGreaterElements(int[] nums){
        int length = nums.length;
        int[] res = new int[length];
        Stack<Integer> stack =  new Stack<>();
        Arrays.fill(res, -1);
        for (int i = 0; i < length * 2; i++) {
            int index = i%length;
            while (!stack.isEmpty() && nums[stack.peek()]<nums[index] ){
                res[stack.pop()] = nums[index];
            }
            stack.push(index);
        }
        return res;
    }

}
