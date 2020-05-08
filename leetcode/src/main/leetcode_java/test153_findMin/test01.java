package test153_findMin;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {4,4,4,4,4,4,4,3,3,3,1,1,1,1,1,1};
        int res = findMin(nums);
        System.out.println(res);

    }
    static public int findMin(int[] nums){
        if (nums.length<=0) return 0;
        int left = 0;
        int right = nums.length-1;
        if (nums[left]<nums[right]) return nums[left];
        while (left<right){
            int mid = left+(right-left)/2;
            if ( mid+1<=nums.length-1&&nums[mid]>nums[mid+1])return nums[mid+1];
            if (mid-1>=0&&nums[mid]<nums[mid-1])return nums[mid];
            if (nums[mid]>=nums[left]){
                left=mid+1;
            }else {
                right = mid-1;
            }
        }
        return nums[0];
    }
}
