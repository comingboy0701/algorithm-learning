package test16_threeSumClosest;

import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {
        int[] num = {-1,-2,-3,1,2,3,0};
        int target = 1;
        int result = threeSumClosest(num,target);
        System.out.println(result);
    }
    public static int threeSumClosest(int[] num, int target){

        Arrays.sort(num);
        int result = num[0] + num[1] + num[2];
        Arrays.sort(num);
        for (int i = 0; i <num.length ; i++) {
            int first = num[i];
            if (i>0 && first==num[i-1]) continue;
            int left= i+1;
            int right =num.length-1;
            while (left<right){
                int sum = first+num[left]+num[right];
                if(Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
                if (sum > target){
                    right--;
                    while (left<right &&num[right]==num[right+1]) right--;
                }else if (sum< target){
                    left++;
                    while (left<right &&num[left]==num[left-1]) left++;
                    }
                else {
                    return result;
                }
            }
        }
        return result;
    }
}
