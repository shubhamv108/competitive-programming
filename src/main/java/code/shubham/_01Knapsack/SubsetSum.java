package code.shubham._01Knapsack;

public class SubsetSum {

    class Solution {
        boolean solve(int[] A, int t) {
            return recurse(A, A.length - 1, t, new Boolean[A.length][t+1]);
        }

        boolean recurse(int[] A, int n, int t, Boolean[][] dp) {
            if (t == 0)
                return true;

            if (n < 0 || t < 0)
                return false;

            if (dp[n][t] != null)
                return dp[n][t];

            return dp[n][t] = recurse(A, n - 1, t,  dp) || recurse(A, n - 1, t - A[n],  dp);
        }
    }

}
