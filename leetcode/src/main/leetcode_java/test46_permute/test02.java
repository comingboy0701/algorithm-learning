package test46_permute;

import java.util.ArrayList;
import java.util.List;

public class test02 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> list = permute(nums);
        System.out.println(list);

    }
    static public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return list;
        core(nums,0,list);
        return list;
    }

    private static void core(int[] nums,int index,List<List<Integer>> list){
        if (index == nums.length){
            List<Integer> list1 = new ArrayList<>();
            for (int i=0;i<nums.length;i++){
                list1.add(nums[i]);
            }
            list.add(list1);
            return;
        }
        for (int i=index;i<nums.length;i++){
            swap(nums,i,index);
            core(nums,index+1,list);
            swap(nums,i,index);
        }
    }

    private static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
