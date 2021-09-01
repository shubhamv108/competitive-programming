package code.dynamicprogramming;

public class BurstBalloons {

    /**
     * https://www.youtube.com/watch?v=KWPat-qNAGI
     */
    private class Solution {
        public int maxCoins(int[] nums) {
            int l = nums.length;
            if (l == 0) return 0;

            int[][] dp = new int[l][l];

            for (int len = 0; len < l; len++) {
                for (int i = 0; i + len < l; i++) {
                    int j = i + len ;
                    int leftNum  = i == 0 ? 1 : nums[i - 1];
                    int rightNum = j == l - 1 ? 1 : nums[j + 1];
                    for (int k = i; k <= j; k++) {
                        int left  = k == i ? 0 : dp[i][k - 1];
                        int right = k == j ? 0 : dp[k + 1][j];
                        dp[i][j] = Math.max(dp[i][j],
                                left + (leftNum * nums[k] * rightNum) + right);
                    }
                }
            }
            return dp[0][l - 1];
        }
    }

}
