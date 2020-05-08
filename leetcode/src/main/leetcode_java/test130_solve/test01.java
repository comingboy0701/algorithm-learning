package test130_solve;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));;
        }
        System.out.println("---------------------");
        solve(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));;
        }

    }

    static public void solve(char[][] board) {
        int lenRow = board.length;
        int lenCol = board[0].length;
        for (int i=0;i<lenRow;i++){
            for (int j = 0; j < lenCol; j++) {
                boolean isBorder = j==0||i==0||i==lenRow-1||j==lenCol-1;
                if(isBorder&&board[i][j]=='O'){
                    dfs(board,i,j);
                }
            }
        }
        for (int i=0;i<lenRow;i++){
            for (int j = 0; j < lenCol; j++) {
                if (board[i][j]=='O'){
                    board[i][j]='X';
                }
                if (board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }
    static public void dfs(char[][] board,int i,int j){
        if(i<0||j<0||i>board.length-1||j>board[0].length-1||board[i][j]=='#'||board[i][j]=='X'){
            return;
        }
        board[i][j] = '#';
        dfs(board,i+1,j);
        dfs(board,i,j+1);
        dfs(board,i-1,j);
        dfs(board,i,j-1);
    }

}
