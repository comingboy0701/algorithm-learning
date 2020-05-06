package test36_isValidSudoku;

import java.util.HashMap;

public class test01 {
    public static void main(String[] args) {
        char[][] nums =  {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
    };
        boolean result = isValidSudoku(nums);
        System.out.println(result);
    }
    static public  boolean isValidSudoku(char[][] nums){
        HashMap[] rows = new HashMap[9];
        HashMap[] columns = new HashMap[9];
        HashMap[] boxes = new HashMap[9];
        for (int i = 0; i <9 ; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        if (nums.length==0) return false;
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                char num = nums[i][j];
                if (num!='.'){
                    int boxes_index = (i/3)*3+j/3;
                    int n = (int) num;
                    if (rows[i].containsKey(n)||columns[j].containsKey(n)||boxes[boxes_index].containsKey(n)){
                        return false;
                    }
                    else {
                        rows[i].put(n,1);
                        columns[j].put(n,1);
                        boxes[boxes_index].put(n,1);
                    }

                }

            }

        }
        return  true;
    }
}
