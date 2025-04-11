package code.shubham._01Knapsack.Unbounded;

public class CoinChangeII {
    class Solution {
        public int change(int A, int[] C) {
            int[] dp = new int[A + 1];
            dp[0] = 1;
            for (int c : C)
                for (int a = c; a <= A; ++a)
                    dp[a] += dp[a-c];

            return dp[A];
        }
    }
}
