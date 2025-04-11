package code.shubham._01Knapsack.LongestCommonSubstring;

public class MinimumNumberOfDeletionsAndInsertions {
    class Solution {
        public int minOperations(String s1, String s2) {
            return s1.length() + s2.length() - (2 * lcs(s1, s2));
        }

        public int lcs(String a, String b) {
            return recurse(a, 0, b, 0, new Integer[a.length()][b.length()]);
        }

        int recurse(String a, int ai, String b, int bi, Integer[][] dp) {
            if (ai == a.length() || bi == b.length())
                return 0;

            if (dp[ai][bi] != null)
                return dp[ai][bi];

            if (a.charAt(ai) == b.charAt(bi))
                return dp[ai][bi] = 1 + recurse(a, ai + 1, b, bi + 1, dp);

            return dp[ai][bi] =  Math.max(recurse(a, ai + 1, b, bi, dp), recurse(a, ai, b, bi + 1, dp));
        }
    }
}
