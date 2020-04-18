package test03动态规划;

import org.junit.Test;

import static org.junit.Assert.*;

public class test01_lengthOfLISTest {
    @Test
    public void lengthOfLIS() throws Exception {
        int[] nums = {1,2,3,1,2};
        int max = new test01_lengthOfLIS().lengthOfLIS(nums);
        System.out.println(max);
    }

}