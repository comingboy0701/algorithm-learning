package test03动态规划;

import org.junit.Test;

import java.util.Arrays;

public class test05_KMPTest {
    @Test
    public void KMP() throws Exception {
        String pat= "ABABC";
        int[][] dp  = new test05_KMP().KMP(pat);
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }

}