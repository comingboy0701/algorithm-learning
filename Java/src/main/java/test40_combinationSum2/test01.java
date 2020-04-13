package test40_combinationSum2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = combinationSum2(candidates,target);
        System.out.println(result);
    }

    static public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates == null) return res;
        int start = 0;
        LinkedList<Integer> list = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, start, target, list, res);
        return res;
    }

    static public void dfs(int[] candidates, int start, int target, LinkedList<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new LinkedList<>(list));
            return;
        } else if (target < 0) {
            return;
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] < 0) break;
                if(i>start&&candidates[i-1]==candidates[i] ){
                    continue;
                }
                list.add(candidates[i]);
                dfs(candidates, i+1, target - candidates[i], list, res);
                list.removeLast();
            }
        }

    }
}
