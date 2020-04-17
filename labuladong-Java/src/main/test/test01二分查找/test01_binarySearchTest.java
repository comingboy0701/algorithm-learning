package test01二分查找;

import org.junit.Test;

public class test01_binarySearchTest {

    @Test
    public void binarySearch() throws Exception {
        int[] nums = {1, 2, 2, 3};
        int target = 2;
        // 普通二分查找
        int index = new test01_binarySearch().binarySearch(nums, target);
        System.out.println(index);
        // 左侧二分查找
        index = new test01_binarySearch().left_bound(nums, target);
        System.out.println(index);
        // 右侧二分查找
        index = new test01_binarySearch().right_bound(nums, target);
        System.out.println(index);

    }


}