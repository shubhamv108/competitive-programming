package code.shubham.dynamicprogramming.grids;

public class MaximumPathScoreInAGrid {
    class Solution {
        public int maxPathScore(int[][] A, int K) {
            int m = A.length, n = A[0].length;
            return recurse(A, 0, 0, m, n, K, new Integer[m][n][K+1]);
        }

        int recurse(int[][] A, int r, int c, int m, int n, int K, Integer[][][] dp) {
            if (r < 0 || c < 0 || r == m || c == n)
                return -1;

            if (dp[r][c][K] != null)
                return dp[r][c][K];

            int k = K - (A[r][c] == 0 ? 0 : 1);
            if (k < 0)
                return dp[r][c][K] = -1;

            if (r == m - 1 && c == n - 1)
                return A[r][c];

            int max = Math.max(
                    recurse(A, r, c + 1, m, n, k, dp),
                    recurse(A, r + 1, c, m, n, k, dp));

            return dp[r][c][K] = max == -1 ? -1 : A[r][c] + max;
        }
    }

    static void main() {
        System.out.println(new MaximumPathScoreInAGrid().new Solution().maxPathScore(new int[][] {
            { 0, 1 },
            { 1, 2 }
        }, 1));
    }
}
