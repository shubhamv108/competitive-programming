package code.shubham._01Knapsack.LongestCommonSubstring;

public class LongestIncreasingSubsequence {
    class Solution {
        public int lengthOfLIS(int[] A) {
            int result = 1;
            int[] dp = new int[A.length];
            dp[0] = 1;
            for (int i = 1; i < A.length; ++i) {
                dp[i] = 1;
                for (int j = 0; j < i; ++j)
                    if (A[i] > A[j])
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                result = Math.max(result, dp[i]);
            }
            return result;
        }
    }
}
