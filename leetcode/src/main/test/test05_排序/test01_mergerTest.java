package test05_排序;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class test01_mergerTest {
    @Test
    public void sort() throws Exception {
        int[] arr = {2, 5, 3, 10, -3, 1, 6,4};
        new test01_merger().mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}