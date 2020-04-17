package test02回溯框架;

import java.util.Arrays;
import java.util.List;

public class test02_solveNQueens {
    // N 皇后问题
    public static void main(String[] args) {
        String[][] board = new String[3][3];
        Arrays.fill(board,".");
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

}
