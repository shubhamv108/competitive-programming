package code.shubham.dynamicprogramming;

public class MaximumScoreUsingExactlyKPairs {
    class Solution {
        long negInf = Long.MIN_VALUE >> 1;
        public long maxScore(int[] A, int[] B, int k) {
            return recurse(A, 0, B, 0, k, new Long[A.length + 1][B.length + 1][k + 1]);
        }

        long recurse(int[] A, int ai, int[] B, int bi, int k, Long[][][] dp) {
            if (k == 0)
                return 0;

            if (ai == A.length || bi == B.length || (A.length - ai) < k || (B.length - bi) < k)
                return negInf;

            if (dp[ai][bi][k] != null)
                return dp[ai][bi][k];

            long max = Math.max(recurse(A, ai+1, B, bi, k, dp), recurse(A, ai, B, bi+1, k, dp));
            return dp[ai][bi][k] = Math.max(max, ((long) A[ai] * B[bi]) + recurse(A, ai+1, B, bi+1, k-1, dp));
        }
    }

    class Solution2 {
        long NEG_INF = Long.MIN_VALUE >> 1;
        public long maxScore(int[] A, int[] B, int k) {
            int al = A.length;
            int bl = B.length;

            long[][] prev = new long[al + 1][bl + 1];
            long[][] curr = new long[al + 1][bl + 1];

            for (int p = 1; p <= k; ++p) {

                for (int i = 0; i <= al; ++i)
                    for (int j = 0; j <= bl; ++j)
                        curr[i][j] = NEG_INF;

                for (int i = p; i <= al; ++i)
                    for (int j = p; j <= bl; ++j) {
                        curr[i][j] = Math.max(
                                curr[i][j],
                                Math.max(curr[i - 1][j], curr[i][j - 1]));

                        long score = (long) A[i - 1] * B[j - 1];
                        curr[i][j] = Math.max(curr[i][j], prev[i - 1][j - 1] + score);
                    }


                long[][] temp = prev;
                prev = curr;
                curr = temp;
            }

            return prev[al][bl];
        }
    }
}
