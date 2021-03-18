package test213_rob;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,1};
        int res = new test01().rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        //可以偷第一家，但不能偷最后一家
        int robFirst = robHelper(nums, 0, nums.length - 2);
        //可以偷最后一家，但不能偷第一家
        int robLast = robHelper(nums, 1, nums.length - 1);
        //选择偷第1家和不偷第1家结果的最大值
        return Math.max(robFirst, robLast);
    }

    private int robHelper(int[] nums, int start, int end) {
        int steal = 0, noSteal = 0;
        for (int j = start; j <= end; j++) {
            int temp = steal;
            steal = noSteal + nums[j];
            noSteal = Math.max(noSteal, temp);
        }
        return Math.max(steal, noSteal);

    }
}
