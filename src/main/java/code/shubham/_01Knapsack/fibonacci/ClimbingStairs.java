package code.shubham._01Knapsack.fibonacci;

public class ClimbingStairs {
    class Solution {
        public int climbStairs(int n) {
            return f(n, new Integer[n+1]);
        }

        int f(int n, Integer[] dp) {
            if (n <= 2)
                return n;
            if (dp[n] != null)
                return dp[n];
            return dp[n] = f(n-1, dp) + f(n-2, dp);
        }
    }
}
