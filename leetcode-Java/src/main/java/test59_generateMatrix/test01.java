package test59_generateMatrix;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int n = 5;
        int[][] result = generateMatrix(n);
        for (int i = 0; i < result.length; i++) {
            int[] tmp = result[i];
            System.out.println(Arrays.toString(tmp));
        }

    }
    static public int[][] generateMatrix(int n){
        int num = 1;
        int last = n*n;
        int l = 0,r = n-1,t = 0,b = n-1;
        int[][] Matrix = new int[n][n];
        while (num<=last){
            for (int i = l; i <=r ; i++) {Matrix[t][i] = num++ ;}
            t++;
            for (int i = t; i <=b ; i++) {Matrix[i][r] = num++;}
            r--;
            for (int i = r; i >=l ; i--) {Matrix[b][i] = num++;}
            b--;
            for (int i = b; i >=t ; i--) {Matrix[i][l] = num++;}
            l++;
        }
        return Matrix;
    }
}
