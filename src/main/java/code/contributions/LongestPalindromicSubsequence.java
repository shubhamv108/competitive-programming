package code.contributions;

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

    public static void main(String[] args) {
        System.out.println(
                new LongestPalindromicSubsequence().get("bbbab")
        );
    }
}
