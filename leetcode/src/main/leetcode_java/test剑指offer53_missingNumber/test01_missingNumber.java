package test剑指offer53_missingNumber;

public class test01_missingNumber {
    public static void main(String[] args) {
        int[] nums = {0,2};
        int res =  new test01_missingNumber().missingNumber(nums);
        System.out.println(res);
    }

    public int missingNumber(int[] nums) {
        int right = 0;
        int left = nums.length-1;
        while (right<=left){
            int mid = (right+left)/2;
            if (nums[mid]==mid){
                right = mid+1;
            }else {
                left = mid-1;
            }
        }
        return right;
    }
}
