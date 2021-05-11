package test424_characterReplacement;

public class test01 {

    public static void main(String[] args) {
        String s = "AAAA";
        int k =2;
        int res = new test01().characterReplacement(s, k);
        System.out.println(res);
    }

    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length -left;
    }




}
