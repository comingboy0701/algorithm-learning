package test118_generate;

import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int numRows = 2;
        List<List<Integer>> list = generate(numRows);
        System.out.println(list);
    }
    static public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new LinkedList<>();
        if (numRows<1) return  list;
        List<Integer> tmp = new LinkedList<>();
        tmp.add(1);
        list.add(tmp);
        if (numRows==1) return list;
        for (int i=1;i<numRows;i++){
            List<Integer> tmp1 = list.get(i-1);
            List<Integer> tmp2 = new LinkedList<>();
            tmp2.add(1);
            for (int j = 0; j <tmp1.size()-1 ; j++) {
                int num = tmp1.get(j)+tmp1.get(j+1);
                tmp2.add(num);
            }
            tmp2.add(1);
            list.add(tmp2);
        }
        return list;
    }
}
