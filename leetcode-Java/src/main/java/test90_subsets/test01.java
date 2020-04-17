package test90_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] nums ={1,2,2,3};
        List<List<Integer>> list = subsetsWithDup(nums);
        System.out.println(list);
    }
    static  public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>(){{add(new ArrayList<Integer>());}};
        int count = 0;
        for (int i = 0; i <nums.length; i++) {
            int size = i>0&&nums[i]==nums[i-1]?list.size()-count:0;
            count=0;
            int len = list.size();
            for (int j = size; j<len; j++) {
                List<Integer> suSets = new ArrayList<>(list.get(j));
                suSets.add(nums[i]);
                list.add((suSets));
                count++;
            }
        }
        return list;
    }
}
