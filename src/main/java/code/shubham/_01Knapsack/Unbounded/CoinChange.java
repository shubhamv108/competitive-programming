package code.shubham._01Knapsack.Unbounded;

public class CoinChange {

    class Solution {
        public int coinChange(int[] C, int A) {
            int[] dp = new int[A+1];
            for (int a = 1; a <= A; ++a) {
                dp[a] = Integer.MAX_VALUE;
                for (int c : C) {
                    if (a < c || dp[a - c] == Integer.MAX_VALUE)
                        continue;
                    dp[a] = Math.min(dp[a], dp[a - c] + 1);
                }
            }

            return dp[A] == Integer.MAX_VALUE ? -1 : dp[A];
        }
    }

}
