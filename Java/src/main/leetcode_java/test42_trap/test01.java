package test42_trap;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(nums);
        System.out.println(result);

    }
    static  public int trap(int[] height) {

        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int tmp = 0;
        for (int i = 0; i < height.length; i++) {
            if (tmp<height[i]){
                tmp = height[i];
            }
            left[i] = tmp;
        }
        tmp = 0;
        for (int i = height.length-1; i > 0; i--) {
            if (tmp<height[i]){
                tmp = height[i];
            }
            right[i] = tmp;

        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i]<left[i] && height[i]<right[i]){
                tmp = Math.min(left[i],right[i])-height[i];
                result = result+tmp;
            }
        }

        return result;
    }
}
