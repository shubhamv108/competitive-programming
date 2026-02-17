package code.shubham.dynamicprogramming;

public class MaxSumAfterMerginfAdjacentNumbers {
    class Solution {
        int solve(int[] A) {
            return recurse(A, 0, new Integer[A.length]);
        }

        int recurse(int[] A, int ai, Integer[] dp) {
            if (ai == A.length)
                return 0;

            int max = A[ai] + recurse(A, ai + 1, dp);
            if (ai < A.length - 1) {
                int digits =  (int) Math.floor(Math.log10(Math.abs(A[ai + 1]))) + 1;;
                max = Math.max(max, merge(A[ai], A[ai+1]) + recurse(A, ai + 2, dp));
            }
            return dp[ai] = max;
        }

        int merge(int x, int y) {
            return Integer.parseInt(String.valueOf(x) + String.valueOf(y));
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxSumAfterMerginfAdjacentNumbers().new Solution().solve(new int[] { 2, 2, 3, 5, 4, 0 }));
        System.out.println(new MaxSumAfterMerginfAdjacentNumbers().new Solution().solve(new int[] { 3, 19, 191, 91, 3 }));
        System.out.println(new MaxSumAfterMerginfAdjacentNumbers().new Solution().solve(new int[] { 12, 6, 18, 10, 1, 0 }));
        System.out.println(new MaxSumAfterMerginfAdjacentNumbers().new Solution().solve(new int[] { 2, 1, 0, 1, 2, 9, 1, 0 }));
    }
}
