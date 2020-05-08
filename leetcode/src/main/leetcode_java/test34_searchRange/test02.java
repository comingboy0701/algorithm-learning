package test34_searchRange;

import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {
        int[] nums = {};
        int[] result = searchRange(nums, 4);
        System.out.println(Arrays.toString(result));
    }

    static public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int[] result = {-1, -1};
        if (len == 0) return result;
        int left = 0;
        int right = len - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[mid] < target) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else if (nums[mid] == target) {
                int left2 = mid;
                int right1 = mid;

                result[0] = mid;
                result[1] = mid;

                while (left< left2 && nums[left2-1]==target) { // 只有两种情况，
                    result[0] = left2-1;
                    left2 = left2-1;
                    mid = left + (left2 - left)/2;
                    if (nums[mid] == target) { //nums[mid]==target,target 在左边还存在
                        left2 = mid;
                        mid = left + (left2 - left)/2;
                        result[0] = left2;

                    } else if (nums[mid] < target) { //nums[mid]<target,只可能存在右边
                        left = mid + 1;
                        mid = left + (left2 - left) / 2;
                    }
                }


                while (right1 < right && nums[right1+1]==target) { // 只有两种情况，
                    result[1] = right1+1;
                    right1 = right1+1;
                    mid = right1 + (right - right1) / 2;
                    if (target == nums[mid]) { // nums[mid]==target,target 在右边还存在
                        right1 = mid;
                        mid = right1 + (right - right1) / 2;
                        result[1] = right1;
                    } else if (target < nums[mid]) { //只存在左边
                        right = mid;
                        mid = right1 + (right - right1) / 2;
                    }
                }
                break;

            } else if (nums[mid] > target) {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        return result;
    }
}
