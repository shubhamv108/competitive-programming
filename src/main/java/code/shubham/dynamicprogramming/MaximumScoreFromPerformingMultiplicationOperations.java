package code.shubham.dynamicprogramming;

public class MaximumScoreFromPerformingMultiplicationOperations {

    // O(2^multipliers.length)
    class Solution {
        public int maximumScore(int[] A, int[] multipliers) {
            return f(A, multipliers, 0, A.length - 1, 0);
        }

        int f(int[] A, int[] multipliers, int l, int r, int m) {
            if (m == multipliers.length)
                return 0;
            return Math.max(
                     A[l] * multipliers[m] + f(A, multipliers, l+1, r, m+1),
                    (A[r] * multipliers[m] + f(A, multipliers, l, r-1, m+1)) );
        }
    }

    // O(multipliers.length ^ 3)
    class Solution2 {
        public int maximumScore(int[] A, int[] multipliers) {
            return f(A, multipliers, 0, A.length - 1, 0, new Integer[A.length+1][A.length+1][multipliers.length+1]);
        }

        int f(int[] A, int[] multipliers, int l, int r, int m, Integer[][][] dp) {
            if (m == multipliers.length)
                return 0;
            if (dp[l][r][m] != null)
                return dp[l][r][m];
            return dp[l][r][m] =
                    Math.max( A[l] * multipliers[m] + f(A, multipliers, l+1, r, m+1, dp),
                             (A[r] * multipliers[m] + f(A, multipliers, l, r-1, m+1, dp)) );
        }
    }

    // O(multipliers.length ^ 2)
    class Solution3 {
        public int maximumScore(int[] A, int[] multipliers) {
            return f(A, multipliers, 0, 0, new Integer[A.length+1][multipliers.length+1]);
        }

        int f(int[] A, int[] multipliers, int l, int m, Integer[][] dp) {
            int r = A.length - 1 - (m - l);
            if (m == multipliers.length)
                return 0;
            if (dp[l][m] != null)
                return dp[l][m];
            return dp[l][m] =
                    Math.max( A[l] * multipliers[m] + f(A, multipliers, l+1, m+1, dp),
                             (A[r] * multipliers[m] + f(A, multipliers, l, m+1, dp)) );
        }
    }

    // Since i is limited to 1000, the left pointer l cannot be larger than 1000.
    class Solution4 {
        public int maximumScore(int[] A, int[] multipliers) {
            return f(A, multipliers, 0, 0, new Integer[Math.min(A.length+1, multipliers.length+1)][multipliers.length+1]);
        }

        int f(int[] A, int[] multipliers, int l, int m, Integer[][] dp) {
            int r = A.length - 1 - (m - l);
            if (m == multipliers.length)
                return 0;
            if (dp[l][m] != null)
                return dp[l][m];
            return dp[l][m] =
                    Math.max( A[l] * multipliers[m] + f(A, multipliers, l+1, m+1, dp),
                             (A[r] * multipliers[m] + f(A, multipliers, l, m+1, dp)) );
        }
    }

}
