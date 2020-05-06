package test15_threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] num = {-1,-2,-3,1,2,3,0};
        List<List<Integer>> result = threeSum(num);
        System.out.println(result);
    }
    public static List<List<Integer>> threeSum(int[] num){

        List<List<Integer>> list = new ArrayList<>();
        if (num==null||num.length<3) return list;
        Arrays.sort(num);
        for (int i = 0; i <num.length ; i++) {
            int first = num[i];
            if (first>0) break;
            if (i>0 && first==num[i-1]) continue;
            int left= i+1;
            int right =num.length-1;
            while (left<right){
                int sum = first+num[left]+num[right];
                if (sum==0){
                    list.add(Arrays.asList(first,num[left],num[right]));
                    while (left<right &&num[left]==num[left+1]) left++;
                    while (left<right &&num[right]==num[right-1]) right--;
                    left++;
                    right--;
                }else {
                    if (sum<0){
                        left++;
                    }else {
                        right--;
                    }
                }

            }
        }
        return list;
    }
}
