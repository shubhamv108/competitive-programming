package code.shubham.strings;

public class LongestRepeatedSubsequence {
    class Solution {
        public int LongestRepeatingSubsequence(String S)
        {
            char[] s = S.toCharArray();
            int len = s.length;

            int[][] dp = new int[len+1][len+1];

            for (int i = 1; i <= len; i++) {
                for (int j = 1; j <= len; j++) {
                    if (s[i-1] == s[j-1] && i != j)
                        dp[i][j] =  1 + dp[i-1][j-1];
                    else
                        dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }

            return dp[len][len];
        }
    }
}