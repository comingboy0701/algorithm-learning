package test02回溯框架;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class test01_permuteTest {
    @Test
    public void permute() throws Exception {
        int[] nums = {1,2,3};
        List<List<Integer>> lists = new test01_permute().permute(nums);
        System.out.println(lists);
    }


}