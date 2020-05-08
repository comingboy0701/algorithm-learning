package test22_generateParenthesis;

import java.util.ArrayList;
import java.util.List;

public class test01_dfs {
    public static void main(String[] args) {
        int num =3;
        List<String> list = generateParenthesis(num);
        System.out.println(list);
    }

    static public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) return res;
        dfs("", n, n, res);
        return res;
    }

    static public void dfs(String cur, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(cur);
            return;
        }
        if (left > right) return;
        if (right > 0) dfs(cur + ")", left, right - 1, res);
        if (left > 0) dfs(cur + "(", left - 1, right, res);
    }
}
