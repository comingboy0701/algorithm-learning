package test120_minimumTotal;


import java.util.LinkedList;
import java.util.List;

class tes01{
    public static void main(String[]args){
        List<List<Integer>> triangle = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(1);
        triangle.add(new LinkedList<>(tmp));
        tmp.clear();
        tmp.add(3);
        tmp.add(4);
        triangle.add(new LinkedList<>(tmp));
        System.out.println(triangle);
        int result = minimumTotal(triangle);
        System.out.println(result);
    }

    static  public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len<1) return 0;
        for (int i = len-2; i >= 0; i--) {
            List<Integer> list_up= triangle.get(i);
            List<Integer> list_low= triangle.get(i+1);
            for (int j =0;j<list_up.size();j++){
                int num= list_up.get(j)+Math.min(list_low.get(j),list_low.get(j+1));
                list_up.set(j,num);
            }
        }
        return triangle.get(0).get(0);
    }
}
