package test63_uniquePathsWithObstacles;


public class test01 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,1}};
        int result = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }

    static public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid==null) return 0;
        if (obstacleGrid.length==0) return 0;
        if (obstacleGrid[0].length==0) return 0;
        if (obstacleGrid[0][0]==1) return 0;
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                obstacleGrid[i][j] = obstacleGrid[i][j]==1?-1:0;
            }
        }
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < row; i++) {
            obstacleGrid[i][0] =obstacleGrid[i][0]!=-1?obstacleGrid[i-1][0]:0;
        }
        for (int i = 1; i < column; i++) {
            obstacleGrid[0][i] =obstacleGrid[0][i]!=-1?obstacleGrid[0][i-1]:0;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j]!=-1) {
                    int top = obstacleGrid[i][j - 1] == -1 ? 0 : obstacleGrid[i][j - 1];
                    int left = obstacleGrid[i - 1][j] == -1 ? 0 : obstacleGrid[i - 1][j];
                    obstacleGrid[i][j] = left + top;
                }
            }
        }

        return obstacleGrid[row-1][column-1]==-1?0:obstacleGrid[row-1][column-1];
    }
};