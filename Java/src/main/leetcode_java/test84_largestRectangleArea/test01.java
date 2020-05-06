package test84_largestRectangleArea;

import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int area = largestRectangleArea(heights);
        System.out.println(area);
    }

     static public int largestRectangleArea(int[] heights) {
        int area = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek()!=-1 && heights[i]<=heights[stack.peek()]){
                area = Math.max(area,heights[stack.pop()]*(i-1-stack.peek()));
            }
            stack.push(i);
        }
        while (stack.peek()!=-1){
            area = Math.max(area,heights[stack.pop()]*(heights.length-1-stack.peek()));
        }
        return area;
    }
}
