package test81_search;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {3,1,1};
        int target = 3;
        boolean result = search(nums,target);
        System.out.println(result);
    }

    static public boolean search(int[] nums, int target) {
        int index = search_index(nums,target);
        return index !=-1;
    }

    static public int search_index(int nums[], int target) {
        int len = nums.length;
        if (len == 0) return -1;
        if (len == 1 && target == nums[0]) return 0;
        if (len == 1 && target != nums[0]) return -1;

        int left = 0;
        int right = len - 1;
        int mid = (right - left) / 2;

        while (mid + 1<len-1 && left <= right && mid > 0) {
            if (nums[mid + 1] < nums[mid]) {
                break;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[left]){
                left = mid;
            }
            else {
                left = mid;
            }
            mid = left + (right - left) / 2;
        }

        left = 0;
        right = len - 1;


        if (target <= nums[mid] && target >= nums[left]) {
            mid = searchMid(nums, target, left, mid);
        } else if (target <= nums[right] && target >= nums[mid + 1]) {
            mid = searchMid(nums, target, mid + 1, right);
        } else {
            return -1;
        }

        return mid;
    }

    static int searchMid(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
