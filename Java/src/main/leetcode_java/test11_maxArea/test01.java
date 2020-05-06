package test11_maxArea;

public class test01
{
    public static void main(String[] args) {
        int[] height =  new int[]{1,2,4,5,6,7};
        float area = maxArea(height);
        System.out.println(area);
    }
    public static float maxArea(int[] height){
        float area = 0;
        for (int i = 0; i <height.length-1 ; i++) {
            for (int j = i+1; j <height.length ; j++) {
                float tmp = Math.max(area, Math.min(height[i], height[j]) * (j - i));
                area = Math.max(tmp,area);
            }
        }
        return area;
    }
}
