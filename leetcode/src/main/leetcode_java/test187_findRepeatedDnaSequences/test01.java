package test187_findRepeatedDnaSequences;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class test01 {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = new test01().findRepeatedDnaSequences(s);
        System.out.println(res);
    }

    public List<String> findRepeatedDnaSequences(String s){
        Map<String,Integer> map  = new HashMap<>();
        int left = 0;
        int right = 10;
        while (right<=s.length()){
            String tmp = s.substring(left,right);
            if (map.containsKey(tmp)){
                map.put(tmp,map.get(tmp)+1);
            }else {
                map.put(tmp,1);
            }
            left++;
            right++;
        }
        List<String> res = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()>1) res.add(entry.getKey());
        }
        return res;
    }
}
