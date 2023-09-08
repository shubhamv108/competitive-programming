package code.shubham.oa.kredx;

import java.util.Arrays;

public class LongestPalindromicSubstringForEachPrefix {

    class Solution {
        int[] solve(String s) {
            int[] dp = new int[s.length()];

            lps(s, dp);

            for (int i = 1; i < s.length(); i++)
                dp[i] = Math.max(dp[i], dp[i-1]);

            return dp;
        }

        void lps(String s, int[] dp) {
            for (int  i = 0; i < s.length(); i++) {
                expand(s, i, i, dp);
                expand(s, i, i + 1, dp);
            }
        }

        int expand(String s, int l , int r, int[] dp) {
            while (l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }

            l++;
            r--;

            dp[r] = Math.max(dp[r], r - l + 1);
            return r - l + 1;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
            new LongestPalindromicSubstringForEachPrefix(). new Solution().solve("abaaabbbaa")
        ).forEach(e -> System.out.print(e + " "));
    }

}
