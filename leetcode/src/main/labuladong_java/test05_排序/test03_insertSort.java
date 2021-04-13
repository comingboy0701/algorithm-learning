package test05_排序;

import java.util.Arrays;

public class test03_insertSort {
    public static void main(String[] args) {
        int[] arr = {3,1,2,3};
        new test03_insertSort().insertSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public void insertSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int pre = i-1;
            int current = arr[i];
            while ( pre>=0 && arr[pre]>current){
                arr[pre+1] = arr[pre];
                pre=pre-1;
            }
            arr[pre+1] = current;
        }
    }
}
