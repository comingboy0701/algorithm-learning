package test41_firstMissingPositive;

public class test01 {
    public static void main(String[] args) {
        int[] nums =  {1,1,1};
        int result = firstMissingPositive(nums);
        System.out.println(result);
    }
    static public int firstMissingPositive(int[] nums) {
        if (nums.length==0) return 1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i]>0&&nums[i]<len&&nums[i]-1!=i && nums[i]!=nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i]-1!=i) return i+1;
        }
         return len+1;

    }
    static public void swap(int[] nums,int index1,int index2){
        int tmp = nums[index1];
        nums[index1] = nums[index2] ;
        nums[index2] =tmp ;
        return;
    }
}
