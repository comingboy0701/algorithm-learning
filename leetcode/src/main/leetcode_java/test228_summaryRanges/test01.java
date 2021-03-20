package test228_summaryRanges;

import java.util.LinkedList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int[] nums = {};
        List<String> res =  new test01().summaryRanges(nums);
        System.out.println(res);

    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 == nums.length || nums[i] + 1 != nums[i + 1]) {
                StringBuilder ans = new StringBuilder();
                ans.append(nums[j]);
                if (nums[j] != nums[i]) {
                    ans.append("->");
                    ans.append(nums[i]);
                }
                res.add(ans.toString());
                j = i + 1;
            }
        }
        return res;
    }

}
