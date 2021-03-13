package test200_numIslands;

public class test01 {
    public static void main(String[] args) {
        char[][] grid = {{'1','1','0'},{'1','1','0'},{'1','1','0'}};
        int res = new test01().numIslands(grid);
        System.out.println(res);
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    res+=1;
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j){
         if  (i<0 || i>=grid.length || j< 0 || j>=grid[0].length||grid[i][j] == '0'){
             return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
