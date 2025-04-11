package code.shubham._01Knapsack.LongestCommonSubstring;

public class LongestCommonSubstring {

    class Solution {
        int solve(String a, String b) {
            int result = 0;
            int[][] dp = new int[2][b.length() + 1];
            for (int ai = 1; ai <= a.length(); ++ai) {
                for (int bi = 1; bi <= b.length(); ++bi) {
                    if (a.charAt(ai - 1) == b.charAt(bi - 1)) {
                        dp[1][bi] = dp[0][bi - 1] + 1;
                        result = Math.max(result, dp[1][bi]);
                    }
                }
                dp[0] = dp[1];
                dp[1] = new int[b.length() + 1];
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().new Solution().solve("pikachu", "pikachuisanelectricmouse"));
    }

}
