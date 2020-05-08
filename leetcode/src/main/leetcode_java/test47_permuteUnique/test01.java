package test47_permuteUnique;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,1, 2};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(list);

    }


    static public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        LinkedList tmp = new LinkedList<>();
        if (nums == null) return list;
        Arrays.sort(nums);
        boolean[] use = new boolean[nums.length];
        list = dfs(nums, tmp, list,use);
        return list;
    }

    static public List<List<Integer>> dfs(int[] nums, LinkedList<Integer> tmp, List<List<Integer>> list,boolean[] use) {
        if (tmp.size() == nums.length) {
            list.add(new LinkedList(tmp));
        }
        for (int i = 0; i < nums.length; i++) {
            if(use[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1]&&use[i - 1]==false) continue;
            tmp.add(nums[i]);
            use[i] = true;
            dfs(nums, tmp, list,use);
            tmp.removeLast();
            use[i] = false;
        }
        return list;
    }
}
