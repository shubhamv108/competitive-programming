package code.shubham._01Knapsack.PalindromicSubsequence;

public class PalindromePartitioningII {

    class Solution {
        public int minCut(String s) {
            int n = s.length();
            int[] dp = new int[n];
            for (int i = 0; i < n; ++i)
                dp[i] = i;

            for (int i = 0; i < n; i++) {
                solve(s, i, i,     n, dp);
                solve(s, i, i + 1, n, dp);
            }
            return dp[n-1];
        }

        public void solve(String s, int l, int r, int n, int[] dp) {
            while (l > -1 && r < n && s.charAt(l) == s.charAt(r)) {
                if (l == 0)
                    dp[r] = 0;
                else
                    dp[r] = Math.min(1 + dp[l-1], dp[r]);

                --l;
                ++r;
            }
        }
    }

}
