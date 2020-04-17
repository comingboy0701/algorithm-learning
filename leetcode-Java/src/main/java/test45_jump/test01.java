package test45_jump;


public class test01 {
    public static void main(String[] args) {
        int[] nums = {1,3,3};
        int step = jump(nums);
        System.out.println(step);
    }

    static public int jump(int[] nums){
        int step = 0;
        int postions = 0;
        int end = 0;
        for (int i = 0; i < nums.length-1; i++) {
            postions = Math.max(nums[i]+i,postions);
            if (i==end){
                end = postions;
                step++;
            }
        }
        return step;
    }
}
