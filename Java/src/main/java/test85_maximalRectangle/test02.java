package test85_maximalRectangle;

import test84_largestRectangleArea.test01;

public class test02 {
    public static void main(String[] args) {
        char[][] matrix ={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int result = maximalRectangle(matrix);
        System.out.println(result);

    }

    static public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int[] dp = new int[matrix[0].length];
        int area = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            area = Math.max(area, test01.largestRectangleArea(dp));
        }
        return area;
    }

}
