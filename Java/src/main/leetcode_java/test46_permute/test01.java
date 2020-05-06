package test46_permute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = permute(nums);
        System.out.println(list);
    }


    static public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        LinkedList tmp = new LinkedList();
        list = dfs(nums, list, tmp);
        return list;
    }

    static public List<List<Integer>> dfs(int[] nums, List<List<Integer>> list, LinkedList tmp) {

        if (tmp.size()==nums.length){
            list.add(new LinkedList(tmp));
        }

        for (int i = 0; i < nums.length; i++) {
            if (!tmp.contains(nums[i])){
                tmp.add(nums[i]);
                dfs(nums,list,tmp);
                tmp.removeLast();
            }
        }
        return list;
    }
}
