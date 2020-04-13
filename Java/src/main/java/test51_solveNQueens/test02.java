package test51_solveNQueens;

import java.util.LinkedList;
import java.util.List;

public class test02 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> list = permute(nums);
        System.out.println(list);
    }
   static public List<List<Integer>> permute(int[] nums){
       List<List<Integer>> list = new LinkedList<>();
       LinkedList<Integer> tmp = new LinkedList<>();
       list = dfs(nums,tmp,list);
       return list;
    }

    static public List<List<Integer>> dfs(int[] nums,LinkedList<Integer> tmp,List<List<Integer>> list){
       if (nums.length==tmp.size()){
           list.add(new LinkedList<>(tmp));
       }
       for (int i= 0 ;i<nums.length;i++){
           if (tmp.contains(nums[i])) continue;
           tmp.add(nums[i]);
           dfs(nums,tmp,list);
           tmp.removeLast();
        }
    return list;
    }
}
