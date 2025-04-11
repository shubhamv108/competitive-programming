package code.shubham._01Knapsack.PalindromicSubsequence;

public class LongestPalindromicSubsequence {

    // Time: O(n^2), Space: O(n^2)
    class Solution {
        public int longestPalindromeSubsequence(String s) {
            return recurse(s, 0, s.length() - 1, new Integer[s.length() + 1][s.length() + 1]);
        }

        int recurse(String s, int l, int r, Integer[][] dp) {
            if (l > r)
                return 0;
            if (l == r)
                return 1;

            if (dp[l][r] != null)
                return dp[l][r];

            if (s.charAt(l) == s.charAt(r))
                return dp[l][r] = 2 + recurse(s, l+1, r-1, dp);

            return dp[l][r] = Math.max(recurse(s, l + 1, r, dp), recurse(s, l, r - 1, dp));
        }

    }

}
