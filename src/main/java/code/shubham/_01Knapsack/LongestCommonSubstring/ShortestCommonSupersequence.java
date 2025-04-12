package code.shubham._01Knapsack.LongestCommonSubstring;

public class ShortestCommonSupersequence {
    class Solution {
        public String shortestCommonSupersequence(String a, String b) {
            StringBuilder result = new StringBuilder();
            String lcs = lcs(a, b);
            int ai = 0, bi = 0;
            for (int i = 0; i < lcs.length(); ++i, ++ai, ++bi) {
                char c = lcs.charAt(i);
                while (a.charAt(ai) != c)
                    result.append(a.charAt(ai++));
                while (b.charAt(bi) != c)
                    result.append(b.charAt(bi++));
                result.append(c);
            }
            return result.append(a.substring(ai)).append(b.substring(bi)).toString();
        }

        public static String lcs(String a, String b) {
            int[][] dp = new int[a.length() + 1][b.length() + 1];

            for (int ai = 1; ai <= a.length(); ++ai) {
                for (int bi = 1; bi <= b.length(); ++bi) {
                    if (a.charAt(ai - 1) == b.charAt(bi - 1))
                        dp[ai][bi] = 1 + dp[ai - 1][bi - 1];
                    else
                        dp[ai][bi] = Math.max(dp[ai - 1][bi], dp[ai][bi - 1]);
                }
            }

            StringBuilder lcs = new StringBuilder();
            int ai = a.length(), bi = b.length();
            while (ai > 0 && bi > 0) {
                if (a.charAt(ai - 1) == b.charAt(bi - 1)) {
                    lcs.append(a.charAt(ai - 1));
                    --ai;
                    --bi;
                } else if (dp[ai - 1][bi] > dp[ai][bi - 1]) {
                    --ai;
                } else {
                    --bi;
                }
            }

            return lcs.reverse().toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new ShortestCommonSupersequence().new Solution().shortestCommonSupersequence("abac", "cab"));
    }
}
