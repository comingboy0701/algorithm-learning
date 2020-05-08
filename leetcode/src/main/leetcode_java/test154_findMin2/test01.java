package test154_findMin2;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {2,2,2,0,1};
        int res = finMin(nums);
        System.out.println(res);
    }

    static public int finMin(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (nums[mid]<nums[right]){
                right=mid;
            } else if(nums[mid]>nums[right]){
                left=mid+1;
            }else {
                right=right-1;
            }
        }
        return nums[left];
    }

}
