package test48_rotate;


public class test01 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
        }
    }
   static public void rotate(int[][] matrix) {
        if (matrix==null) return;
        int left = 0;
        int right = matrix.length-1;
       while (left<right){
           for (int s = left,e = right;s<right;s++,e--){
                int tmp = matrix[left][s];
                matrix[left][s] = matrix[e][left];
                matrix[e][left] = matrix[right][e];
                matrix[right][e] = matrix[s][right];
                matrix[s][right] = tmp;
           }
           right--;
           left++;
       }
    }

//    static public void rotate(int[][] matrix) {
//        int temp=-1;
//        for(int start=0,end=matrix[0].length-1;start<end;start++,end--){
//            for(int s=start,e=end;s<end;s++,e--){
//                temp=matrix[start][s];
//                matrix[start][s]=matrix[e][start];
//                matrix[e][start]=matrix[end][e];
//                matrix[end][e]=matrix[s][end];
//                matrix[s][end]=temp;
//            };
//        };
//    }
}
