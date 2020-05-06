package test55_canJump;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {2,0,0};
        boolean result = canJump(nums);
        System.out.println(result);
     }
    static public boolean canJump(int[] nums){
//        if (nums.length==1&&nums[0]==0) return true;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]==0){
                int j = i-1;
                while(j>=0 && nums[j]+j<=i ){
                    j--;
                }
                if (j<0) return false;
            }
        }
        return true;
    }
}
