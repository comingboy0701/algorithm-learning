package test179_largestNumber;

import java.util.Arrays;

public class test02_sort_lambda {
    public static void main(String[] args) {
        int[][] nums = {{6,2},{2,3},{2,1},{5,3},{5,2},{4,3}};
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
        Arrays.sort(nums,(o1,o2)->{
            if(o1[0]==o2[0]) return o1[1]-o2[1];//升序
            else return o1[0]-o2[0];//升序
        });
        System.out.println("============");
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }
}
