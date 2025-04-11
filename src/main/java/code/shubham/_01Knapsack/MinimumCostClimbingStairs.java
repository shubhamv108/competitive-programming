package code.shubham._01Knapsack;

public class MinimumCostClimbingStairs {
    class Solution {
        public int minCostClimbingStairs(int[] A) {
            Integer[] dp = new Integer[A.length];
            return Math.min(recurse(A, 0, dp), recurse(A, 1, dp));
        }

        int recurse(int[] A, int ai, Integer[] dp) {
            if (ai >= A.length)
                return 0;

            if (dp[ai] != null)
                return dp[ai];

            return dp[ai] = A[ai] + Math.min(recurse(A, ai + 1, dp), recurse(A, ai + 2, dp));
        }
    }

    class Solution2 {
        public int minCostClimbingStairs(int[] A) {
            for(int i = 2; i < A.length; ++i)
                A[i] += Math.min(A[i-1], A[i-2]);
            return Math.min(A[A.length - 1], A[A.length - 2]);
        }
    }
}
