package code.shubham.dynamicprogramming;

import java.util.HashMap;

public class ArithmeticSlicesIISubsequence {

    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int result = 0;
            int l = nums.length;
            HashMap<Integer, Integer>[] dp = new HashMap[l];
            for (int i = 0; i < l; i++) {
                dp[i] = new HashMap<>();
                for (int j = i - 1; j > -1; j--) {
                    int d = nums[i] - nums[j];
                    int c = dp[i].getOrDefault(d, 0) + 1;
                    dp[i].put(d, c);
                    if (c >= 3) result++;
                }
            }
            return result;
        }
    }

}
