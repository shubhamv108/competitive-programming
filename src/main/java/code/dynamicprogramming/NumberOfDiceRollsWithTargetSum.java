package code.dynamicprogramming;

public class NumberOfDiceRollsWithTargetSum {
    class Solution {
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 0 || target < 0)
                return target == 0 ? 1 : 0;
            int ways = 0;
            for (int i = 1; i <= f; i++)
                ways = (ways + numRollsToTarget(d - 1, f, target - i)) % 1000000007;
            return ways;
        }
    }
    class Solution2 {
        public int numRollsToTarget(int d, int f, int target) {
            return f(d, f, target, new Integer[d+1][target+1]);
        }

        int f(int d, int f, int target, Integer[][] dp) {
            if (d == 0 || target < 0)
                return target == 0 ? 1 : 0;
            if (dp[d][target] != null)
                return dp[d][target];
            int ways = 0;
            for (int i = 1; i <= f; i++)
                ways = (ways + f(d - 1, f, target - i, dp)) % 1000000007;
            return dp[d][target] = ways;
        }
    }

    class Solution3 {
        public int numRollsToTarget(int d, int f, int target) {
            int[][] dp = new int[d + 1][target + 1];
            dp[0][0] = 1;
            for(int i = 1; i <= d; i++)
                for(int j = 1;j <= target; j++)
                    for(int k = 1; k <= f; k++)
                        dp[i][j] = (dp[i][j] + (k <= j ? dp[i-1][j-k] : 0)) % 1000000007;
            return dp[d][target];
        }
    }
}
