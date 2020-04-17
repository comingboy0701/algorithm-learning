package test77_combine;

import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        List<List<Integer>> list = combine(n, k);
        System.out.println(list);

    }

    static public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new LinkedList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        dfs(n, k,1, tmp,list);
        return list;
    }

    static public void dfs(int n, int k, int index,LinkedList tmp,List<List<Integer>> list) {
        if (tmp.size()==k){
            list.add(new LinkedList<>(tmp));
            return;
        }
        for (int start =index;start<=n-(k - tmp.size()) + 1;start++){ // 剪枝
            tmp.add(start);
            dfs(n, k,start+1, tmp,list);
            tmp.removeLast();
        }
    }
}
