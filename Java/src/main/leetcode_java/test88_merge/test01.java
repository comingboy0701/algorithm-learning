package test88_merge;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[] nums1= {0};
        int m = 0;
        int[] nums2 ={1};
        int n = 1;
        merge( nums1,  m,  nums2,n);
        System.out.println(Arrays.toString(nums1));

    }
    static  public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 =m-1;
        int index2 =n-1;
        int index = m+n-1;
        int tmp = 0;
        while(index1>=0 || index2>=0){
            if(index1>=0 && index2>=0){
                if(nums1[index1]>nums2[index2]){
                     tmp = nums1[index1];
                    index1--;
                }else{
                     tmp = nums2[index2];
                    index2--;
                }
            }else if(index1>=0 && index2<0){
                 tmp = nums1[index1];
                index1--;
            }else if(index2>=0 && index1<0){
                 tmp = nums2[index2];
                index2--;
            }
            nums1[index] = tmp;
            index--;
        }
    }
}
