package code.shubham.dynamicprogramming;

public class MinimumInsertionsToMakeStringPalindromic {
    class Solution {
        public int minInsertions(String s) {
            if (s == null || s.isEmpty()) return 0;
            int len = s.length();
            if (len == 1) return 0;
            char[] chrs = s.toCharArray();

            int lps = lps(s, len);
            return len - lps;
        }


        int lps(String s, int len) {
            char[] chrs = s.toCharArray();
            int[][] dp = new int[len][len];
            for (int i = len - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i+1; j < len; j++)
                    if (chrs[i] == chrs[j])
                        dp[i][j] = 2 + dp[i+1][j-1];
                    else
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
            return dp[0][len - 1];
        }
    }
}
