package test49_groupAnagrams;

import java.util.*;

public class test02 {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> list = groupAnagrams(strs);
        System.out.println(list);
    }
    static public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String key = new String(str);
            if (map.containsKey(key)){
                List<String> list = map.get(key);
                list.add(strs[i]);
                map.put(key,list);
            }else {
                List<String> list = new LinkedList<>();
                list.add(strs[i]);
                map.put(key,list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
