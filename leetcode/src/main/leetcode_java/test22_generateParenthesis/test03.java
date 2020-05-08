package test22_generateParenthesis;
import java.util.ArrayList;
import java.util.LinkedList;

public class test03 {
    static ArrayList<String> result = new ArrayList<>();
    public static void main(String[] args) {
        int n = 3;
        generateParenthesis(n);
        System.out.println(result);
    }
    static protected void generateParenthesis(int n){
        LinkedList<String> list = new LinkedList();
        dfs(list,n,n);
    }

    static protected void dfs(LinkedList<String> list,int left,int right){
        if (left==0&&right==0){
            StringBuilder tmp = new StringBuilder();
            for (String s : list) {
                tmp.append(s);
            }
            result.add(tmp.toString());
        }else if(left>right){
            return;
        }
        else if(left<0||right<0){
            return;
        }
        else if(left<=right){
            if (left>0){
                list.add("(");
                dfs(list,left-1,right);
                list.removeLast();
            }
            if(right>0){
                list.add(")");
                dfs(list,left,right-1);
                list.removeLast();
            }
        }
    }
}
