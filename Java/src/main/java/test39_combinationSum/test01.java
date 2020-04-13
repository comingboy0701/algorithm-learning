package test39_combinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> result = combinationSum(candidates,target);

        System.out.println(result);
    }

    static List<List<Integer>> res =  new LinkedList<>();
    static public List<List<Integer>> combinationSum(int[] candidates, int target){
        Arrays.sort(candidates);
        if (target<=0||candidates==null) return res;
        LinkedList<Integer> list = new LinkedList<>();
        dfs(candidates,0,target,list);
        return res;
    };
    static public void dfs(int[] candidates, int start,int target, LinkedList<Integer> list ){
        if (target==0) {
            res.add(new LinkedList<>(list));
            return;
        }else if (target<0){
            return ;
        }else if (target>0){
            for (int i = start;i < candidates.length;i++) {
                if (target-candidates[i]<0){
                    break;
                }else if (target-candidates[i]>=0){
                    list.add(candidates[i]);
                    dfs(candidates,i,target-candidates[i],list);
                    list.removeLast();
                }
            }
        }
    }
}
