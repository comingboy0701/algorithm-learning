package test面试题_08_10;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[][] image = {{1,1,1},{0,1,0},{0,0,0}};

        for (int i = 0; i < image.length; i++) {
            System.out.println(Arrays.toString(image[i]));
        }

        int sr = 0, sc = 0, newColor = 2;
        int[][] res = new test01().floodFill(image, sr, sc,newColor);
        System.out.println("---------------");

        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }

    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dp(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    public void dp(int[][] image, int sr, int sc, int newColor, int originalColor){
        if (sr<0 || sc<0 || sr>= image.length||sc>=image[0].length) {
            return;
        }
        if (image[sr][sc]==newColor){
            return;
        }

        if(image[sr][sc]==originalColor){
            image[sr][sc] = newColor;
            dp(image, sr-1,sc, newColor, originalColor);
            dp(image, sr+1,sc, newColor, originalColor);
            dp(image, sr,sc-1, newColor, originalColor);
            dp(image, sr,sc+1, newColor, originalColor);
        }

    }
}
