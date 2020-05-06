package test79_exist;

public class test01 {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E' }, {'S', 'F', 'C', 'S' }, {'A', 'D', 'E', 'E' }};
        String word = "ABCE";
        boolean result = exist(board,word);
        System.out.println(result);
    }

    static public boolean exist(char[][] board, String word) {
        int row = board.length;
        if (row == 0) return false;
        int col = board[0].length;
        boolean[][] mask = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, word, i, j, 0, mask)) {
                    return true;
                }
            }
        }
        return false;
    }

    static public boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] mask) {
        if (board[i][j] != word.charAt(index)) return false;
        if (word.length() - 1 == index) return true;
        mask[i][j] = true;
        if (inMatrix(i + 1, j, board) && !mask[i+1][j]&&dfs(board, word, i + 1, j, index+1, mask) ||
                inMatrix(i , j+1, board) && !mask[i][j+1]&&dfs(board, word, i, j + 1, index+1, mask) ||
                inMatrix(i- 1, j, board) && !mask[i-1][j]&&dfs(board, word, i - 1, j, index+1, mask) ||
                inMatrix(i , j-1, board) && !mask[i][j-1]&&dfs(board, word, i, j - 1, index+1, mask)) {
            return true;
        } else {
            mask[i][j] = false;
            return false;
        }

    }

    static public boolean inMatrix(int i, int j, char[][] board) {
        if (i >= board.length || i < 0) return false;
        if (j >= board[0].length || j < 0) return false;
        return true;
    }

}
