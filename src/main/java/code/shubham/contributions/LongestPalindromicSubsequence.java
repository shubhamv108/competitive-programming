package code.shubham.contributions;

public class LongestPalindromicSubsequence {

    public int get(String s) {
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

    class Solution2 {
        int solve(String s) {
            int len = s.length();
            char[] chrs = s.toCharArray();
            int[][] dp = new int[len][len];
            for (int i = len - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i+1; j < s.length(); j++)
                    if (chrs[i] == chrs[j])
                        dp[i][j] = dp[i+1][j-1] + 2;
                    else
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
            return dp[0][len-1];
        }
    }

    public static void main(String[] args) {
//        System.out.println(
//                new LongestPalindromicSubsequence().get("bbbab")
//        );
        System.out.println(
                new LongestPalindromicSubsequence().new Solution2().solve("bbbab")
        );
    }
}
