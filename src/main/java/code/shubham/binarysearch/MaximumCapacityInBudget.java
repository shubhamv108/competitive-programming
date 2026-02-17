package code.shubham.binarysearch;

import java.util.Arrays;

public class MaximumCapacityInBudget {
    class Solution {
        public int maxCapacity(int[] B, int[] C, int T) {
            int[][] A = new int[B.length][3];
            for (int i = 0; i < A.length; ++i) {
                A[i][0] = B[i];
                A[i][1] = C[i];
            }
            Arrays.sort(A, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
            int maxIndex = -1;
            if (A[0][0] < T)
                maxIndex = 0;
            A[0][2] = A[0][1];
            for (int i = 1; i < A.length; ++i) {
                A[i][2] = Math.max(A[i][1], A[i-1][2]);
                if (A[i][0] < T)
                    maxIndex = i;
            }

            int result = 0;
            for (int i = maxIndex; i >= 0; --i) {
                int p = search(A, 0, i - 1, T - A[i][0]);
                if (p == -1)
                    result = Math.max(result, A[i][1]);
                else
                    result = Math.max(result, A[i][1] + A[p][2]);
            }

            return result;
        }

        int search(int[][] A, int l, int r, int t) {
            int idx = -1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (A[m][0] >= t)
                    r = m - 1;
                else {
                    idx = m;
                    l = m + 1;
                }
            }
            return idx;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumCapacityInBudget().new Solution().maxCapacity(new int[] {4,6}, new int[] {5,3}, 3));
    }
}
