package code.strings;

public class PalindromePartitioningII {

    class Solution {
        public int minCut(String s) {
            char[] c = s.toCharArray();
            int n = c.length;
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) dp[i] = i;
            for (int i = 0; i < n; i++) {
                solve(c, dp, i, i);
                solve(c, dp, i, i+1);
            }
            return dp[n-1];
        }

        public void solve(char[] c,int[] dp, int l, int r) {
            int n = c.length;
            while(l > -1 && r < n && c[l] == c[r]) {
                if (l == 0) {
                    dp[r] = 0;
                }
                else {
                    dp[r] = Math.min(1 + dp[l-1], dp[r]);
                }
                l--;
                r++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PalindromePartitioningII().new Solution().minCut("aab")
        );
    }

}
