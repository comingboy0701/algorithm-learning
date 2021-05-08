package test873_lenLongestFibSubseq;

public class test01 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int res = new test01().lenLongestFibSubseq(arr);
        System.out.println(res);
    }
    public int lenLongestFibSubseq(int[] arr) {
        int MaxLen = 0;
        int[][] dp = new int[arr.length][arr.length];
        for (int j = 2; j < arr.length; j++) {
            int left = 0;
            int right = j-1;
            while (left<right){
                int sum = arr[left]+arr[right];
                if (sum>arr[j]){
                    right--;
                }else if(sum<arr[j]){
                    left++;
                }else {
                    dp[right][j] = dp[left][right]+1;
                    MaxLen = Math.max(MaxLen, dp[right][j]);
                    left++;
                    right--;
                }
            }
        }
        return MaxLen>0?MaxLen+2:0;
    }
}
