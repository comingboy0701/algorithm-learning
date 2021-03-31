package test752_openLock;

import java.util.*;

public class test01 {
    public static void main(String[] args) {
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int res = new test01().openLock(deadends, target);
        System.out.println(res);
    }

    public int openLock(String[] deadends, String target) {
        String start = "0000";
        if (target.equals(start)) return 0;

        Queue<String> q = new LinkedList<>();
        q.add(start);

        HashSet<String> set = new HashSet<>(Arrays.asList(deadends));
        if (set.contains(start)){
            return -1;
        }
        HashSet<String> vistied = new HashSet<>();
        vistied.add(start);
        int level =1;
        while (!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String str = q.poll();
                for (int j = 0; j < 4; j++) {
                    char ch = str.charAt(j);
                    String strAdd= str.substring(0,j) + (ch=='9'?0:ch-'0'+1) + str.substring(j+1);
                    String strsub= str.substring(0,j) + (ch=='0'?9:ch-'0'-1) + str.substring(j+1);
                    if (target.equals(strAdd) || target.equals(strsub) ){
                        return level;
                    }
                    if (!set.contains(strAdd) && !vistied.contains(strAdd) ){
                        q.add(strAdd);
                        vistied.add(strAdd);
                    }
                    if (!set.contains(strsub) && !vistied.contains(strsub) ){
                        q.add(strsub);
                        vistied.add(strsub);
                    }
                }
            }
            level++;
        }
        return  -1;
    }
}
