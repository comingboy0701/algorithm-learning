package test131_partition;

import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        String s="aabc";
        int min = minCut(s);
        System.out.println(min);

    }
    static public int minCut(String s) {
        List<List<String>> res = partition(s);
        int min = Integer.MAX_VALUE;
        for (List<String> re : res) {
            min = Math.min(min,re.size());
        }
        return min-1;
    }

    static public List<List<String>> partition(String s) {
        List<List<String>> list = new LinkedList<>();
        if (s==null) return list;
        LinkedList<String> path = new LinkedList();

        list = dfs(list,0,path,s);
        return list;
    }
    static public List<List<String>> dfs(List<List<String>> list,int start,LinkedList<String>path,String s){
        if (start==s.length()){
            list.add(new LinkedList<>(path));
        }
        for(int index = start;index<s.length();index++){
            if(!isPalindrome(s,start,index)){
                continue;
            }
            path.add(s.substring(start,index+1));
            dfs(list,index+1,path,s);
            path.removeLast();
        }
        return list;
    }

    static boolean isPalindrome(String s,int left,int right){
        while (left<right){
            if(s.charAt(left)==s.charAt(right)){
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }
}
