package test80_removeDuplicates;


public class test01 {
    public static void main(String[] args) {
        int[] nums = {};
        int len = removeDuplicates(nums);
        System.out.println(len);
    }
    static public int removeDuplicates(int[] nums) {
        if (nums.length==0) return 0;
        int tmp = nums[0];
        int count = 1;
        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]==tmp){
                count++;
            }else {
                tmp=nums[i];
                count=1;
            }
            if (count<=2){
                left++;
                nums[left]=tmp;
            }
        }
//        System.out.println(Arrays.toString(nums));
        return left+1;
    }

}
