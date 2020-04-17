package test26_removeDuplicates;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {};
        int res = removeDuplicates(nums);
        System.out.println(res);
    }

    static public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (nums.length <= 1) return len;
        int num = 0;
        int start = 1;
        while (start < len) {
            if (nums[num] != nums[start]) {
                num++;
                nums[num++] = nums[start];
            }
            start++;
        }
        return num+1;
    }
}
