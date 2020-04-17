package test52_totalNQueens;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int n = 8;
        int result = totalNQueens(n);
        System.out.println(result);
    }


    static public List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }

    static public int totalNQueens(int n) {
        if (n <= 0) return 0;
        List<List<String>> list = new LinkedList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        list = dfs(0, board,list);
        int size = list.size();
        return size;
    }

    static public List<List<String>> dfs(int row, char[][] board, List<List<String>> list) {
        if (row==board.length){
            list.add(charToString(board));
            return list;
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isVoild(board, row, col)) continue;
            board[row][col] = 'Q';
            dfs(row + 1, board, list);
            board[row][col] = '.';
        }
        return list;
    }

    static public boolean isVoild(char[][] board,int row,int col){
        int rows = board.length;
        // 检查列
        for (char[] chars : board) {
            if (chars[col]=='Q') return false;
        }
        // 右上角
        for (int i = row-1,j=col+1; i >= 0&&j<rows;i--,j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 左上角
        for (int i = row-1,j=col-1; i >= 0&&j>=0;i--,j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}
