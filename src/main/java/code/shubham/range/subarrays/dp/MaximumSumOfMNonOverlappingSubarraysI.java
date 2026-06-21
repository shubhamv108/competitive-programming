package code.shubham.range.subarrays.dp;

import java.util.ArrayDeque;
import java.util.Arrays;

public class MaximumSumOfMNonOverlappingSubarraysI {

    class Solution {
        public long maximumSum(int[] A, int m, int l, int r) {
            int n = A.length;

            long[] pre = new long[n + 1];
            for (int i = 1; i <= n; ++i)
                pre[i] = pre[i - 1] + A[i - 1];

            long[][] dp = new long[m + 1][n + 1];
            for (int i = 0; i <= m; ++i)
                Arrays.fill(dp[i], Long.MIN_VALUE);

            for (int i = 0; i <= n; ++i)
                dp[0][i] = 0;

            for (int j = 1; j <= m; ++j) {
                ArrayDeque<Integer> dq = new ArrayDeque<>();
                for (int i = 1; i <= n; ++i) {
                    dp[j][i] = dp[j][i - 1];

                    int add = i - l;
                    if (add >= 0 && dp[j - 1][add] != Long.MIN_VALUE) {
                        long val = dp[j - 1][add] - pre[add];

                        while (!dq.isEmpty()) {
                            int idx = dq.peekLast();
                            if (dp[j - 1][idx] - pre[idx] <= val)
                                dq.pollLast();
                            else
                                break;
                        }
                        dq.offerLast(add);
                    }

                    while (!dq.isEmpty() && dq.peekFirst() < i - r)
                        dq.pollFirst();

                    if (!dq.isEmpty()) {
                        int idx = dq.peekFirst();
                        dp[j][i] = Math.max(
                                dp[j][i],
                                dp[j - 1][idx] - pre[idx] + pre[i]);
                    }
                }
            }

            long result = Long.MIN_VALUE;
            for (int i = 1; i <= m; ++i)
                result = Math.max(result, dp[i][n]);

            return result;
        }
    }

    class Solution2 {
        public long maximumSum(int[] A, int m, int l, int r) {
            return recurse(0, m, 0, A, l, r, new Long[A.length + 1][m+1][2]);
        }

        private long recurse(int idx, int rem, int taken,
                             int[] A, int l, int r, Long[][][] dp) {
            int n = A.length;

            if (idx >= n)
                return taken == 1 ? 0 : Long.MIN_VALUE;

            if (dp[idx][rem][taken] != null)
                return dp[idx][rem][taken];

            long max = Long.MIN_VALUE;

            // Skip current index
            max = Math.max(max, recurse(idx + 1, rem, taken, A, l, r, dp));

            // Start a new subarray
            if (rem > 0) {
                long sum = 0;

                for (int len = 1; len <= r && idx + len <= n; ++len) {
                    sum += A[idx + len - 1];

                    if (len >= l) {
                        long next = recurse(idx + len, rem - 1, 1, A, l, r, dp);
                        if (next != Long.MIN_VALUE)
                            max = Math.max(max, sum + next);
                    }
                }
            }

            return dp[idx][rem][taken] = max;
        }
    }

    void main() {
        System.out.println(new Solution().maximumSum(new int[] { 4, 1, -5, 2 }, 2, 1, 3));
    }
}
