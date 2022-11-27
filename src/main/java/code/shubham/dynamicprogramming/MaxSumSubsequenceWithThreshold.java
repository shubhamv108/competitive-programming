package code.shubham.dynamicprogramming;

import input.InputUtils;

import java.util.Arrays;
import java.util.TreeSet;

public class MaxSumSubsequenceWithThreshold {

    class Solution {
        int[] A;
        int threshold;
        Integer[][] dp;
        Solution(int[] A, int threshold) {
            this.A = A; this.threshold = threshold;
            dp = new Integer[A.length][threshold + 1];
        }

        int sum(int pos, int threshold) {
            if (threshold <= 0) return 0;
            if (threshold - A[pos] < 0) return dp[pos][threshold] = Integer.MIN_VALUE;
            if (dp[pos][threshold] != null) return dp[pos][threshold];
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int i = pos + 1; i < A.length; i++) {
                sum = sum(i, threshold - A[pos]);
                max = Math.max(max, sum);
            }
            return dp[pos][threshold] = max == Integer.MIN_VALUE ? A[pos] : max + A[pos];
        }

        int solve() {
            Arrays.sort(A);
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                if (A[i] == threshold) return A[i];
                dp[i][threshold] = sum(i, threshold);
                max = Math.max(max, dp[i][threshold]);
            }
            return max;
        }

    }

    class Solution2 {
        int solve(int threshold, int... values) {
            TreeSet<Integer> sums = new java.util.TreeSet<>();
            for (int value : values) {
                if (value == threshold)
                    return threshold; // short-circuit
                if (value < threshold) {
                    if (sums.contains(threshold - value))
                        return threshold; // short-circuit
                    for (int prevSum : sums.subSet(1, threshold - value).toArray(new Integer[0]))
                        sums.add(prevSum + value);
                    sums.add(value);
                }
            }
            return (sums.isEmpty() ? 0 : sums.last().intValue());
        }
    }

    public static void main(String[] args) {
        int[] A = InputUtils.nextIntLine();
        System.out.println(
                new MaxSumSubsequenceWithThreshold().new Solution2().solve(20, A)
        );
    }

}
