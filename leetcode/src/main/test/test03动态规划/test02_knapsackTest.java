package test03动态规划;

import org.junit.Test;

import static org.junit.Assert.*;

public class test02_knapsackTest {
    @Test
    public void knapsack() throws Exception {
        int N = 3, W = 4;
        int[] wt = {2, 1, 3},val = {4, 2, 3};
        int max = new test02_knapsack().knapsack(W,N,wt,val);
        System.out.println(max);
    }

}