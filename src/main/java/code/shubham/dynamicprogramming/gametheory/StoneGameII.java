package code.shubham.dynamicprogramming.gametheory;

public class StoneGameII {
    class Solution {
        public int stoneGameII(int[] A) {
            int n = A.length;
            int[] pre = new int[n+1];
            for (int i = 1; i <= n; ++i)
                pre[i] = pre[i-1] + A[i - 1];

            return recurse(pre, 0, true, 1, new Integer[2][n+1][n+1]);
        }

        int recurse(int[] A, int ai, boolean turn, int M, Integer[][][] dp) {
            if (ai == A.length - 1)
                return 0;

            if (dp[turn ? 1 : 0][ai][M] != null)
                return dp[turn ? 1 : 0][ai][M];

            if (turn) {
                int max = 0;
                for (int x = 1; x <= 2 * M && ai + x < A.length; ++x)
                    max = Math.max(max, (A[ai + x] - A[ai]) + recurse(A, ai + x, !turn, Math.max(M, x), dp));
                return dp[turn ? 1 : 0][ai][M] = max;
            } else {
                int min = Integer.MAX_VALUE;
                for (int x = 1; x <= 2 * M && ai + x < A.length; ++x)
                    min = Math.min(min, recurse(A, ai + x, !turn, Math.max(M, x), dp));
                return dp[turn ? 1 : 0][ai][M] = min;
            }
        }
    }

    void main(String[] args) {
        System.out.println(new Solution().stoneGameII(new int[] { 2,7,9,4,4 }));
        System.out.println(new Solution().stoneGameII(new int[] { 1,2,3,4,5,100 }));
    }
}
