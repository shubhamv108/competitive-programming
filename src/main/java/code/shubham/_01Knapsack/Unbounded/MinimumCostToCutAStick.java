package code.shubham._01Knapsack.Unbounded;

import java.util.Arrays;

public class MinimumCostToCutAStick {

    class Solution {
        public int minCost(int n, int[] A) {
            Arrays.sort(A);
            return cut(0, n, A, 0, A.length - 1, new Integer[A.length][A.length]);
        }

        int cut(int nl, int nr, int[] A, int l, int r, Integer[][] dp) {
            if (l > r)
                return 0;

            if (dp[l][r] != null)
                return dp[l][r];

            int min = Integer.MAX_VALUE;
            for (int i = l; i <= r; ++i)
                min = Math.min(min, cut(nl, A[i], A, l, i - 1, dp) + cut(A[i], nr, A, i + 1, r, dp));

            return dp[l][r] = min + (nr - nl);
        }
    }

}
