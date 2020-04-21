package test03动态规划;


import java.util.HashMap;

public class test04_superEggDrop {
    HashMap<Integer[], Integer> dict = new HashMap<Integer[], Integer>();
    public int superEggDrop(int k,int n){
        int min = dfs(k,n);
        return min;
    }
    public int dfs(int k,int n){
        if (n==0) return 0;
        if (k==1) return n;
        int res = Integer.MAX_VALUE;
        Integer[] path = {k,n};
        if (dict.containsKey(path)) return dict.get(path);
        for (int i=1;i<=n;i++){
            res = Math.min(res,Math.max(dfs(k-1,i-1),dfs(k,n-i))+1);
        }
        Integer[] tmp = {k,n};
        dict.put(tmp,res);
        return res;
    }
}
