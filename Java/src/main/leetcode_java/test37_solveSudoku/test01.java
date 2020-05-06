package test37_solveSudoku;

public class test01 {
    public static void main(String[] args) {
        char[][] board = {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
        solveSudoku(board);
    }
    static  boolean[][] rows=new boolean[9][9],cloumns=new boolean[9][9],boxes=new boolean[9][9];
    static  boolean finished=false;

    static void solveSudoku(char[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9 ; j++) {
                if (board[i][j]!='.'){
                    int num = board[i][j]-'1';
                    rows[i][num] = true;
                    cloumns[j][num]= true;
                   boxes[i/3*3+j/3][num] = true;
                }
            }
        }
        dfs(board,0,0);
    }
    static void dfs(char board[][],int row,int col){
        if (row==9){
            for (char[] num : board) {
                System.out.println(num);
            }
            finished = true;
            return;
        }
        if (board[row][col]!='.'){
            if (col==8){
                dfs(board,row+1,0);
            }
            else {
                dfs(board,row,col+1);
            }
        }else {
            int boxes_index = row/3*3+col/3;
            for (int num = 0; num <9 ; num++) {
                if (!rows[row][num]&&!cloumns[col][num]&&!boxes[boxes_index][num]){

                    board[row][col] = (char) (num+'1');

                    rows[row][num] = true;
                    cloumns[col][num]= true;
                    boxes[boxes_index][num] = true;
                    if (col==8){
                        dfs(board,row+1,0);
                    }else {
                        dfs(board,row,col+1);
                    }
                    if (!finished){
                        board[row][col] = '.';
                        rows[row][num] = false;
                        cloumns[col][num]= false;
                        boxes[boxes_index][num] = false;
                    }
                }
            }

        }

    };

}
