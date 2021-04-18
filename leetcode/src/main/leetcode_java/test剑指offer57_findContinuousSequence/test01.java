package test剑指offer57_findContinuousSequence;

import java.util.Arrays;
import java.util.LinkedList;

public class test01 {
    public static void main(String[] args) {
        int target = 5;
        int[][] res = new test01().findContinuousSequence(target);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }

    }

    public int[][] findContinuousSequence(int target) {

        int left = 1;
        int right = 2;
        int sum = left+right;
        LinkedList<int[]> linkedLists = new LinkedList<>();
        while (left<right & right<=target/2+1){
            if (sum<target){
                left = left+1;
                sum =sum+left;
            }
            else if (sum>target){
                sum =sum-right;
                right = right+1;
            }
            else{
                int[] tmp = new int[right-left+1];
                for(int i = left;i<=right;i++){
                    tmp[i-left] = i;
                }
                linkedLists.add(tmp);
                left = left+1;
                right =right+1;
            }
        }

        return linkedLists.toArray(new int[linkedLists.size()][]);
    };
}
