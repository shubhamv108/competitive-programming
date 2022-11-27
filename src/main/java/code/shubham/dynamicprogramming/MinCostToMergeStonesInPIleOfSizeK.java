package code.shubham.dynamicprogramming;

public class MinCostToMergeStonesInPIleOfSizeK {

    class Solution {
        public int mergeStones(int[] stones, int K) {
            int l = stones.length;
            if (l == 0) return 0;
            if ((l - 1) % (K - 1) > 0) return -1;

            int[] prefix = new int[l + 1];
            for (int i = 0; i < l; i++)
                prefix[i + 1] = prefix[i] + stones[i];

            int[][] dp = new int[l][l];

            for (int len = K; len <= l; len++) {
                for (int i = 0; i + len <= l; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k += K - 1)
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    if ((j - i) % (K - 1) == 0)
                        dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
            return dp[0][l-1];
        }
    }

}
