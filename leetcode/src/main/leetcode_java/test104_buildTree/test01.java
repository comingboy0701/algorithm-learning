package test104_buildTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class test01 {
    public static void main(String[] args) {
        ArrayList<Integer> nums =  new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        int[] tmp =  nums.stream().mapToInt(Integer::valueOf).toArray();
        System.out.println(Arrays.toString(tmp));
        LinkedList<Integer> path = new LinkedList<>();
        path.add(2);
        path.removeLast();
        System.out.println(path);

    }


}
