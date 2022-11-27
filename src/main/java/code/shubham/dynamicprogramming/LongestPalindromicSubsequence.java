package code.shubham.dynamicprogramming;

public class LongestPalindromicSubsequence {

    public class Solution {
        public int longestPalindromeSubseq(String s) {
            return recurse(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
        }

        private int recurse(String s, int i, int j, Integer[][] memo) {
            if (memo[i][j] != null) return memo[i][j];
            if (i > j)      return 0;
            if (i == j)     return 1;

            if (s.charAt(i) == s.charAt(j)) {
                memo[i][j] = recurse(s, i + 1, j - 1, memo) + 2;
            } else {
                memo[i][j] = Math.max(recurse(s, i + 1, j, memo),
                                      recurse(s, i, j - 1, memo));
            }
            return memo[i][j];
        }
    }

    class Solution2 {
        public int longestPalindromeSubseq(String s) {
            char[] c = s.toCharArray();
            int[] dp = new int[c.length];
            int max = 0;
            for (int i = 0; i < dp.length; i++ ) {
                dp[i] = 1;
                int curMax = 0;
                for (int j = i - 1; j >= 0; j--) {
                    int prev = dp[j];
                    if (c[i] == c[j])
                        dp[j] = curMax + 2;
                    curMax = Math.max(prev, curMax);
                }
            }
            for (int n : dp) max = Math.max(max, n);
            return max;
        }
    }

}
