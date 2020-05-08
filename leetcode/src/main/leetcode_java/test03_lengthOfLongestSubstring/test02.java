package test03_lengthOfLongestSubstring;

import java.util.HashMap;
import java.util.Map;

public class test02 {
    public static void main(String[] args) {
        String str = "abcabded";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int start = 0,end = 0; end < n; end++) {
            char alpha =  s.charAt(end);
            if (map.containsKey(alpha)){
                start = Math.max(map.get(alpha),start);
            }
            ans = Math.max(end-start+1,ans);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

}





