package code.shubham._01Knapsack.LongestCommonSubstring;

public class LongestIncreasingSubsequence {
    class Solution {
        public int lengthOfLIS(int[] A) {
            int result = 1;
            int[] dp = new int[A.length];
            dp[0] = 1;
            for (int i = 1; i < A.length; ++i) {
                dp[i] = 1;
                for (int j = 0; j < i; ++j)
                    if (A[i] > A[j])
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                result = Math.max(result, dp[i]);
            }
            return result;
        }
    }

    // O(n * log n)
    class Solution2 {
        public int lengthOfLIS(int[] A) {
            int result = 0;

            int[] dp = new int[A.length];
            for (int a : A) {
                int l = 0, r = result;
                while (l != r) {
                    int m = l + (r - l) / 2;
                    if (dp[m] < a)
                        l = m + 1;
                    else
                        r = m;
                }

                dp[l] = a;
                if (l == result)
                    ++result;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().new Solution2().lengthOfLIS(new int[] { 10,9,2,5,3,7,101,18 }));
    }
}
