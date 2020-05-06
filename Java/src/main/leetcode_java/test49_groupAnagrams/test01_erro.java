package test49_groupAnagrams;

import java.util.LinkedList;
import java.util.List;

public class test01_erro {
    public static void main(String[] args) {
        String[] str = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        List<List<String>> list = groupAnagrams(str);
        System.out.println(list);
    }

    static public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new LinkedList<>();
        List<Integer> hashSum = new LinkedList<>();

        for (int i = 0; i < strs.length; i++) {
            String pre = strs[i];
            int hash = 0;
            for (char c : pre.toCharArray()) {
                hash = hash + c;
            }
            int j = 0;
            while (j < hashSum.size()) {
                if (hashSum.get(j) == hash) {
                    String before = list.get(j).get(0);
                    int k = 0;
                    while (k < pre.length()) {
                        if (!before.contains(pre.substring(k, k + 1))) {
                            break;
                        }
                        k++;
                    }
                    if (k == pre.length()) {
                        LinkedList<String> tmp = (LinkedList<String>) list.get(j);
                        tmp.add(pre);
                        list.set(j, tmp);
                        break;
                    }
                }
                j++;
            }
            if (j == hashSum.size()) {
                hashSum.add(hash);
                LinkedList<String> tmp = new LinkedList<>();
                tmp.add(pre);
                list.add(tmp);
            }

        }
        return list;
    }
}
