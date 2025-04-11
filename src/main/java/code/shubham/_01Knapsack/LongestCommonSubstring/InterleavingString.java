package code.shubham._01Knapsack.LongestCommonSubstring;

public class InterleavingString {
    class Solution {
        public boolean isInterleave(String a, String b, String c) {
            if (a.length() + b.length() != c.length())
                return false;

            return recurse(a, b, c, 0, 0, new Boolean[a.length() + 1][b.length() + 1]);
        }

        boolean recurse(String a, String b, String c, int ai, int bi, Boolean[][] dp) {
            if (dp[ai][bi] != null)
                return dp[ai][bi];

            if (ai + bi == c.length() && ai == a.length() && bi == b.length())
                return true;

            boolean result = false;
            if (ai < a.length() && ai+bi < c.length() && a.charAt(ai) == c.charAt(ai+bi))
                result = recurse(a, b, c, ai+1, bi, dp);

            if (bi < b.length() && ai+bi < c.length() && b.charAt(bi) == c.charAt(ai+bi))
                result = result || recurse(a, b, c, ai, bi+1, dp);

            return dp[ai][bi] = result;
        }
    }
}
