package test56_merge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{1,100}};
        int[][] result = merge(intervals);
        for (int i = 0; i < result.length; i++) {
            int[] tmp = result[i];
            System.out.print(Arrays.toString(tmp)+',');
        }
    }

    static public int[][] merge(int[][] intervals) {
        if (intervals.length<=0) return new int[][]{};
        Arrays.sort(intervals, (a, b) -> {
            if(a[0]==b[0]){
                return a[1] - b[1];
            }else {
                return a[0] - b[0];
            }
        });
        List<int[]> list = new LinkedList<>();
        int[] num = intervals[0];
        int left = num[0];
        int right = num[1];
        for (int i = 1; i < intervals.length; i++) {
            num = intervals[i];
            if (right>=num[0]){
                right = Math.max(right,num[1]);
            }else{
                list.add(new int[]{left,right});
                left = num[0];
                right = num[1];
            }
        }
        list.add(new int[]{left,right});

        return  list.toArray(new int[list.size()][2]);
    }

}
