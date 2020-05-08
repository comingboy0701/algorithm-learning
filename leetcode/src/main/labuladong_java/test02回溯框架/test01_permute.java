package test02回溯框架;

import java.util.LinkedList;
import java.util.List;

// n排列的问题
public class test01_permute {
    List<List<Integer>> list = new LinkedList();
    public List<List<Integer>> permute(int[] nums){
        LinkedList<Integer> track = new LinkedList<Integer>();
        backtrack(nums, track);
        return list;
    }

    private void backtrack(int[] nums,LinkedList<Integer>track){
        if(track.size()==nums.length){
            list.add(new LinkedList<Integer>(track));
        }
        for (int i = 0;i<nums.length;i++){
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            backtrack(nums,track);
            track.removeLast();
        }
    }
}
