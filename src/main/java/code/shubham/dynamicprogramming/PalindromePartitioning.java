package code.shubham.dynamicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PalindromePartitioning {

    public class MinCuts {
        public int minCuts (String s) {
            int[][] dp = new int[s.length()][s.length()];
            int j;
            for (int l = 0; l < s.length(); l++) {
                for (int i = 0; i < s.length() - l; i++) {
                    j = i + l;
                    if (i == j || isPalindrome(s, i, j)) dp[i][j] = 0;
                    else dp[i][j] = 1 + minCuts(s, i, j, dp);
                }
            }
            IntStream.range(0, s.length()).forEach(i -> {
                Arrays.stream(dp[i]).forEach(e -> System.out.print(e + " "));
                System.out.println();
            });
            return dp[0][s.length() - 1];
        }

        private int minCuts(String s, int i, int j, int[][] dp) {
            int min = Integer.MAX_VALUE;
            for (int k = i; k < j; k++)
                min = Math.min (min, dp[i][k] + dp[k+1][j]);
            return min;
        }

        private boolean isPalindrome (String s, int i, int j) {
            while (i < j) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().new MinCuts().minCuts("abcba"));
    }
}
