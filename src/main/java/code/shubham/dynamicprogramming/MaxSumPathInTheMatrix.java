package code.shubham.dynamicprogramming;

import java.util.Arrays;

public class MaxSumPathInTheMatrix {
    class Solution {
        public static int getMaxPathSum(int[][] A) {
            int[][] dp = new int[2][A[0].length];
            int[] dirs = new int[] {-1, 1};
            int prev = 0, cur = 1;
            for (int c = 0; c < A[0].length; ++c)
                dp[prev][c] = A[0][c];
            for (int r = 1; r < A.length; ++r) {
                for (int c = 0; c < A[0].length; ++c) {
                    int max = dp[prev][c];
                    for (int dir : dirs) {
                        int cc = c + dir;
                        if (cc < 0 || cc >= A[0].length)
                            continue;
                        max = Math.max(max, dp[prev][cc]);
                    }
                    dp[cur][c] =  A[r][c] + max;
                }
                prev = 1 - prev;
                cur = 1 - cur;
            }

            return Arrays.stream(dp[prev]).max().getAsInt();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxSumPathInTheMatrix().new Solution().getMaxPathSum(
                        new int[][] {
                                {10, 2, 3},
                                { 3, 7, 2},
                                { 8, 1, 5}
                        }
                )
        );
    }
}
