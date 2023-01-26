package code.shubham.dynamicprogramming;

import java.util.Arrays;

public class TargetSum {
    class Solution {
        public int findTargetSumWays(int[] A, int target) {
            int total = Arrays.stream(A).sum();
            return f(A, 0, 0, target, total, new Integer[A.length][2 * total + 1]);
        }

        int f(int[] A, int index, int curSum, int target, int total, Integer[][] dp) {
            if (A.length == index)
                return curSum == target ? 1 : 0;

            if (dp[index][curSum + total] != null)
                return dp[index][curSum + total];

            return dp[index][curSum + total] =
                    f(A, index + 1, curSum + A[index], target, total, dp) +
                    f(A, index + 1, curSum - A[index], target, total, dp);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TargetSum().new Solution().findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3)
        );
    }
}
