package test126_findLadders;

import java.util.*;

public class test01 {
    public static void main(String[] args) {
        String[] tmp = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = new ArrayList<>(Arrays.asList(tmp));
        String beginWord = "hit";
        String endWord = "cog";
//        List<List<String>> res = findLadders(beginWord, endWord, list);
//        System.out.println(res);
        String s1 = "hot";
        String s2 = "dot";
        int num = diffenentWordNum(s1,s2);
        System.out.println(num);

    }

    static public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Queue<List<String>> list = new LinkedList<>();
        List<String> path = new LinkedList<>();
        path.add(beginWord);
        list.add(path);
        ArrayList<String> visited = new ArrayList<>();
        visited.add(beginWord);
        boolean flag = false;
        while (!flag && !list.isEmpty()) {
            int size = list.size();
            ArrayList<String> subvisited = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<String> q = list.poll();
                String ends = q.get(q.size() - 1);
                for (String s1 : wordList) {
                    if (diffenentWordNum(ends, s1) == 1) {
                        path = new LinkedList<>(q);
                        subvisited.add(s1);
                        path.add(s1);
                        if (s1.equals(endWord)) {
                            flag = true;
                            res.add(new LinkedList<>(path));
                        }
                        list.add(path);
                    }
                }
            }
            for (String s : subvisited) {
                wordList.remove(s);
            }
        }
        return res;
    }

    static public int diffenentWordNum(String s1, String s2) {
        int res = 0;
        for (int i = 0; i < s1.length(); i++) {
            String ch1 = s1.substring(i, i+1);
            String ch2 = s2.substring(i,i+1);
            if (!ch1.equals(ch2)){
                res++;
            }
        }
        return res;
    }
}
