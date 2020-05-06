package test78_subsets;


import java.util.LinkedList;
import java.util.List;

public class test01_dfs {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> list = subsets(nums);
        System.out.println(list);
    }

    static public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i =0;i<nums.length+1;i++){
            List<List<Integer>> tmp = combine(nums,i);
            for (List<Integer> list_i : tmp) {
                list.add(list_i);
            }
        }
        return list;
    }


    static public List<List<Integer>> combine(int[] nums, int k) {
        List<List<Integer>> list = new LinkedList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        dfs(nums, k,0, tmp,list);
        return list;
    }

    static public void dfs(int[] nums, int k, int index,LinkedList tmp,List<List<Integer>> list) {
        if (tmp.size()==k){
            list.add(new LinkedList<>(tmp));
            return;
        }
        for (int start =index;start<=nums.length-(k - tmp.size());start++){ // 剪枝
            tmp.add(nums[start]);
            dfs(nums, k,start+1, tmp,list);
            tmp.removeLast();
        }
    }
}
