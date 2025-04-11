package code.shubham._01Knapsack.LongestCommonSubstring;

public class EditDistance {
    class Solution {
        public int minDistance(String a, String b) {
            return recurse(a, b, a.length() - 1, b.length() - 1, new Integer[a.length()][b.length()]);
        }

        int recurse(String a, String b, int ai, int bi, Integer[][] dp) {
            if (ai == -1)
                return bi + 1;

            if (bi == -1)
                return ai + 1;

            if (dp[ai][bi] != null)
                return dp[ai][bi];

            if (a.charAt(ai) ==  b.charAt(bi))
                return dp[ai][bi] = recurse(a, b, ai-1, bi-1, dp);

            return dp[ai][bi] = 1 + Math.min(
                    recurse(a, b, ai-1, bi-1, dp),
                    Math.min(recurse(a, b, ai-1, bi, dp), recurse(a, b, ai, bi-1, dp))
                                            );
        }
    }
}
