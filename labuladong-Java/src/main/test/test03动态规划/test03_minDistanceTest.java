package test03动态规划;

import org.junit.Test;

import static org.junit.Assert.*;

public class test03_minDistanceTest {
    @Test
    public void minDistance() throws Exception {
        String s1 = "rad",s2="apple";
        int min = new test03_minDistance().minDistance(s1,s2);
        System.out.println(min);
    }

}