package test73_setZeroes;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
        for (int i = 0; i <matrix.length ; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    static public void setZeroes(int[][] matrix) {
        boolean row0 = false;
        boolean col0 = false;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0]==0) {
                col0 = true;
                break;
            }
        }
        for (int i = 0; i < col; i++) {
            if (matrix[0][i]==0) {
                row0 = true;
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j]==0){
                    matrix[0][j]=matrix[i][0]=0;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[0][j]==0||matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if (row0){
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }
        if (col0){
            for (int i = 0; i < row; i++) {
                matrix[i][i] =0;
            }
        }
    }
}
