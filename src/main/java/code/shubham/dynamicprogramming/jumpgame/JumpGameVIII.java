package code.shubham.dynamicprogramming.jumpgame;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class JumpGameVIII {
    class BadSolution {
        public long minCost(int[] A, int[] C) {
            return recurse(A, 0, A.length, C);
        }

        long recurse(int[] A, int i, int n, int[] C) {
            if (i == n)
                return Long.MAX_VALUE;

            if (i == n - 1)
                return C[i];

            long minCost = Long.MAX_VALUE;
            boolean kLesser = A[i+1] < A[i];
            boolean kGreaterEqual = A[i+1] >= A[i];
            for (int j = i + 2; j < n; ++j) {
                if ((A[i] <= A[j] && kLesser) || (A[i] > A[j] && kGreaterEqual)) {
                    long r = recurse(A, j, n, C);
                    if (r == Long.MAX_VALUE)
                        continue;
                    minCost = Math.min(minCost, r);
                }

                kLesser = kLesser || (A[j] < A[i]);
                kGreaterEqual = kGreaterEqual || (A[j] >= A[i]);
            }

            return minCost == Long.MAX_VALUE ? -1 : C[i] + minCost;
        }
    }

    class Solution {
        public long minCost(int[] A, int[] C) {
            int n = A.length;
            Deque<Integer> one = new ArrayDeque<>();
            Deque<Integer> two = new ArrayDeque<>();
            long[] dp = new long[n];
            Arrays.fill(dp, Long.MAX_VALUE);
            dp[0] = 0;

            for (int i = 0; i < n; ++i) {
                while(!one.isEmpty() && A[i] >= A[one.peek()])
                    dp[i] = Math.min(dp[i], dp[one.pop()] + C[i]); // condition 1.

                while(!two.isEmpty() && A[i] < A[two.peek()])
                    dp[i] = Math.min(dp[i], dp[two.pop()] + C[i]); // condition 2.

                one.push(i);
                two.push(i);
            }

            return dp[n - 1];
        }
    }

    void main() {
        System.out.println(new JumpGameVIII().new Solution().minCost(
            new int[] { 3, 2, 4, 4, 1 },
            new int[] { 3, 7, 6, 4, 2 }
        ));
    }
}
