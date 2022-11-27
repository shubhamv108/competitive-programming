package code.shubham.dynamicprogramming;

public class MaxLengthSubsequenceWithAdjacentDifference0Or1 {

    class Solution {
        int solve(int[] A) {
            int len = A.length, dp[] = new int[len];
            int result = 0;
            dp[0] = 1;
            for (int i = 1; i < len; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++)
                    if (Math.abs(A[i] - A[j]) <= 1 && dp[i] < dp[j] + 1)
                        dp[i] = dp[j] + 1;
                result = Math.max(result, dp[i]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxLengthSubsequenceWithAdjacentDifference0Or1()
                    .new Solution()
                        .solve(new int[] { 4, 13, 2, 3 })
        );
    }

}
