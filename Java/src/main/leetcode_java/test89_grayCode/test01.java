package test89_grayCode;

import java.util.ArrayList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int n = 3;
        List<Integer> list = grayCode(n);
        System.out.println(list);
    }

    static public List<Integer> grayCode(int n){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = list.size()-1; j >= 0; j--) {
                list.add(head+list.get(j));
            }
            head<<=1;
        }
        return list;
    }

}
