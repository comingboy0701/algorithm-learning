package test42_trap;

public class test02 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(nums);
        System.out.println(result);

    }
    static  public int trap(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length-1;
        int leftMax = 0;
        int rightMax = 0;
        while (left<right){
            leftMax = Math.max(height[left],leftMax);
            rightMax = Math.max(height[right],rightMax);
            if (leftMax<rightMax){
                result = result+leftMax-height[left];
                left++;
            }else {
                result = result+rightMax-height[right];
                right--;
            }
        }

        return result;
    }
}
