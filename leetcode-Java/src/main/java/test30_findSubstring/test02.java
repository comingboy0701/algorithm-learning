package test30_findSubstring;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test02 {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> result = findSubstring(s, words);
        System.out.println(result);
    }

    static public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length < 1) return result;
        int len1 = s.length();
        int word_len = words[0].length();
        int wordL = words.length;
        int len2 = words.length * words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        int[] flagArray = new int[word_len];
        for (int i = 0; i < word_len; i++) {
            int flag = 0;
            for (String word : words) {
                flag += word.charAt(i);
            }
            flagArray[i] = flag;
        }

        for (int i = 0; i < len1 - len2 + 1; i++) {
            HashMap<String, Integer> temp_map = new HashMap(map);

            int sign = 0;

            for (int j = 0; j < word_len; j++) {
                int sumFlag = 0;
                for (int k = 0; k < wordL; k++) {
                    sumFlag += s.charAt(i + k * word_len + j);
                }
                if (sumFlag != flagArray[j]) {
                    break;
                }
                sign++;
            }
            if (sign == word_len) {
                for (int j = 0; j < len2 - word_len + 1; j = j + word_len) {
                    String word_tmp = s.substring(i + j, i + j + word_len);
                    if (temp_map.containsKey(word_tmp)) {
                        temp_map.put(word_tmp, temp_map.get(word_tmp) - 1);
                        if (temp_map.get(word_tmp) == 0) {
                            temp_map.remove(word_tmp);
                        }
                    } else {
                        break;
                    }
                }
                if (temp_map.size() == 0) {
                    result.add(i);
                }
            } else {
                continue;
            }


        }
        return result;
    }
}
