package code.shubham._01Knapsack;

import java.util.ArrayList;
import java.util.List;

public class MaximumEarningsFromTaxi {

    class Solution {
        public long maxTaxiEarnings(int n, int[][] A) {
            List<int[]>[] tripEnds = new ArrayList[n + 1];
            for (int i = 0; i < A.length; ++i) {
                int end = A[i][1];
                if (tripEnds[end] == null)
                    tripEnds[end] = new ArrayList<>();
                tripEnds[end].add(new int[] { A[i][0], end - A[i][0] + A[i][2] });
            }

            long[] dp = new long[n + 1];
            for (int i = 1; i <= n; ++i) {
                dp[i] = dp[i - 1];
                if (tripEnds[i] != null) {
                    for (int[] e: tripEnds[i]) {
                        int start = e[0];
                        int tips = e[1];
                        dp[i] = Math.max(dp[i], dp[start] + tips);
                    }
                }
            }
            return dp[n];
        }
    }

}
