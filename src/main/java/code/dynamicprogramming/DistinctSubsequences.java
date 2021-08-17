package code.dynamicprogramming;

public class DistinctSubsequences {

    class Solution {
        public int numDistinct(String s, String t) {
            int sl = s.length(), tl = t.length();
            return numDistinct(s.toCharArray(), t.toCharArray(), 0, 0, new int[sl][tl]);
        }

        private int numDistinct(char[] chars1, char[] chars2, int s1, int s2, int[][] dp) {
            if (s2 == chars2.length) return 1;
            if (s1 + chars2.length - s2 > chars1.length) return 0;
            if (dp[s1][s2] != 0) return dp[s1][s2] - 1;

            int cnt = numDistinct(chars1, chars2, s1 + 1, s2, dp);
            if (chars1[s1] == chars2[s2]) cnt += numDistinct(chars1, chars2, s1 + 1, s2 + 1, dp);

            dp[s1][s2] = cnt + 1;
            return cnt;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new DistinctSubsequences().new Solution().numDistinct("rabbbit", "rabbit")
        );
    }

}
