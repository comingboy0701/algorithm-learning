package test01二分查找;

public class test01_binarySearch {

    // 最简单的二分查找
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;//注意
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;//注意
            } else if (target > nums[mid]) {
                left = mid + 1;//注意
            }
        }
        return -1;
    }

    // 寻找左侧边界的二分搜索
    public int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid-1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if(left>=nums.length||nums[left]!=target){ // 注意边界
            return -1;
        }
        return left;
    }

    // 寻找右侧边界的二分搜索
    public int right_bound(int[] nums,int target){
        int left=0;
        int right = nums.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target){
                left=mid+1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        if (right<0||nums[right]!=target){ // 注意边界
            return -1;
        }
        return right;
    }

}
