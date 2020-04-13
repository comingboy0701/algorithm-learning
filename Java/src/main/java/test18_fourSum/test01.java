package test18_fourSum;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] num = {-4,0,-4,2,2,2,-2,-2};
        int target = 7;
        List<List<Integer>> result = fourSum(num,target);
        System.out.println(result);
    }
    public static List<List<Integer>> fourSum(int[] num, int target){
        List<List<Integer>> result = new ArrayList<>();

        if (num==null||num.length<4) return result;
        Arrays.sort(num);
        int len = num.length;
        for (int i = 0; i <len-3 ; i++) {
            int first = num[i];
            if (first*4>target) break;
            if (i>0 && first==num[i-1]) continue;
            for (int j = len-1; j >2 ; j--) {
                int sencond = num[j];
                if (sencond*4<target) break;
                if (j<len-1 && sencond==num[j+1]) continue;
                int right = i+1;
                int left = j-1;
                while(right<left){
                    int sum = first+sencond+num[right]+num[left];
                    if (target==sum){
                        result.add(Arrays.asList(first,sencond,num[right],num[left]));
                        while (right<len-2 && num[right]==num[right+1]) right++;
                        while (left>2 && num[left]==num[left-1]) left--;
                        right++;
                        left--;
                    }else if (sum<target){
                            while (right<len-2 && num[right]==num[right+1]) right++;
                            right++;
                    }else {
                            while (left>2 && num[left]==num[left-1]) left--;
                            left--;
                        }
                    }
                }
            }
        return result;
    }
}
