package test11_maxArea;

public class test02 {
    public static void main(String[] args) {
        int[] height =  new int[]{1,2,4,5,6,7};
        float area = maxArea(height);
        System.out.println(area);
    }
    public static float maxArea(int[] height){
        float area = 0;
        int left = 0;
        int right = height.length-1;
        while(left<right){
            float tmp = Math.min(height[left], height[right]) * (right-left);
            area = Math.max(tmp,area);
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }
        }
        return area;
    }

}
