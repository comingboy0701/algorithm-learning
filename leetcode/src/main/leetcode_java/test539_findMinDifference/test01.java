package test539_findMinDifference;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        List<String> time = new LinkedList<>();
        time.add("00:00");
        time.add("23:59");
        int res = new test01().findMinDifference(time);
        System.out.println(res);

    }

    public int findMinDifference(List<String> timePoints) {

        int len = timePoints.size();
        if(len<=1 || len> 24*60){
            return 0;
        }
        int[] times = new int[len];
        for (int i = 0; i < timePoints.size(); i++) {
            times[i] = parseMinute(timePoints.get(i));
        }
        Arrays.sort(times);
        int res = Integer.MAX_VALUE;;
        for (int i = 1; i < times.length; i++) {
            res = Math.min(res, times[i]-times[i-1]);
            if (res==0) return 0;
        }
        res = Math.min(res, 1440+times[0]-times[len-1]);
        return res;

    }
    private int parseMinute(String str){
        return str.charAt(0)*600+str.charAt(1)*60+str.charAt(3)*10+str.charAt(4);

    }
}
