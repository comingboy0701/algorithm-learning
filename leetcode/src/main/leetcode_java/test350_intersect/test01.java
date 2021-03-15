package test350_intersect;

import java.util.Arrays;
import java.util.LinkedList;

public class test01 {
    public static void main(String[] args) {
        int[] num1= {1,2,3};
        int[] num2= {1,2,2};
        int[] res = new test01().intersect(num1, num2);
        System.out.println(Arrays.toString(res));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        LinkedList<Integer> list = new LinkedList<>();
        while (i< nums1.length && j<nums2.length){
            if (nums1[i]==nums2[j]){
                list.add( nums1[i]);
                i++;
                j++;
            }
            else if(nums1[i]<nums2[j]){
                i++;

            }else{
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }

}
