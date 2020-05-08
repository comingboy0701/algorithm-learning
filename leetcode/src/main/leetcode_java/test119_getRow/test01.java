package test119_getRow;

import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int rowIndex = 3;
        List<Integer> list = getRow(rowIndex);
        System.out.println(list);
    }
    static public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<>();
        if (rowIndex<1) return list;
        list.add(1);
        if (rowIndex==1) return list;
        for (int i=1;i<=rowIndex;i++){
            List<Integer> tmp1 = list;
            List<Integer> tmp2 = new LinkedList<>();
            tmp2.add(1);
            for (int j = 0; j <tmp1.size()-1 ; j++) {
                int num = tmp1.get(j)+tmp1.get(j+1);
                tmp2.add(num);
            }
            tmp2.add(1);
            list = tmp2;
        }
        return list;
    }
}
