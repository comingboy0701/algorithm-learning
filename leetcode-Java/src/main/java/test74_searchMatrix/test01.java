package test74_searchMatrix;

public class test01 {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target = 3;
        boolean result = searchMatrix(matrix,target);
        System.out.println(result);
    }
    static public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m*n-1;
        int num,mid;

        while(left<=right){
            mid = (left+right)/2;
            num = matrix[mid/n][mid%n];
            if (num==target) return true;
            if (target<num) right = mid-1;
            if (target>num) left = mid+1;
        }
        return  false;
    }
}
