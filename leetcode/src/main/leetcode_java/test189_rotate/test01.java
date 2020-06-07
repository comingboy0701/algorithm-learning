package test189_rotate;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        new test01().rotate(nums,k);
        System.out.println(Arrays.toString(nums));

    }
    public void rotate(int[] nums,int k){
        int len = nums.length;
        int count=0;
        for (int start = 0; count < nums.length; start++) {
        int current = start;
        int pre = nums[start];
        do{
            int next = (current+k)%len;
            int tmp = nums[next];
            nums[next] = pre;
            current = next;
            pre = tmp;
            count++;
        }while (start!=current);
        }
    }
}
