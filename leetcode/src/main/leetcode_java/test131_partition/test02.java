package test131_partition;
import java.util.ArrayList;
import java.util.List;

public class test02 {
    public static void main(String[] args) {
        String s = "aabc";
        List<List<String>> res = new test02().partition(s);
        System.out.println(res);

    }
    int[][] f;
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;
    public List<List<String>> partition(String s) {
        n = s.length();
        f = new int[n][n];
        dfs(s, 0);
        return res;
    }
    private void dfs(String s, int i){
        if(i==n){
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (isPalindrome(s, i, j)==1){
                ans.add(s.substring(i,j+1));
                dfs(s, j+1);
                ans.remove(ans.size()-1);
            }
        }
    }

    private int isPalindrome(String  s, int i, int j){
        if (f[i][j] !=0){
            return f[i][j];
        }
        if (i>=j){
            f[i][j]=1;
        }else if (s.charAt(i)==s.charAt(j)){
            f[i][j] = isPalindrome(s, i+1, j-1);
        }
        else {
            f[i][j]  = -1;
        }
        return f[i][j];
    }
}
