package test54_spiralOrder;


import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[][] matrix = {};
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result);
    }

    static public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length==0) return list;
        int row = matrix.length-1;
        int col = matrix[0].length-1;
        int row_1 = 0;
        int col_1 = 0;
        int k = 1;
        while (row_1 <=row && col_1 <= col) {
            if (k == 1) {
                for (int i = col_1; i <= col; i++) {
                    list.add(matrix[row_1][i]);
                }
                row_1++;
                for (int i = row_1; i <= row; i++) {
                    list.add(matrix[i][col]);
                }
                col--;
                k = -k;
            } else {
                for (int i = col; i >= col_1; i--) {
                    list.add(matrix[row][i]);
                }
                row--;
                for (int i = row; i >= row_1; i--) {
                    list.add(matrix[i][col_1]);
                }
                col_1++;
                k = -k;
            }

        }
        return list;
    }
}
