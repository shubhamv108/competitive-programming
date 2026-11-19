package code.shubham.heaps;

public class MaximizeSumAfterAtMostKDigitRearrangements {
    class Solution {
        public long maximizeSum(int[] A, int k) {
            int n = A.length;
            int[] max = new int[n];
            for (int i = 0; i < n; ++i)
                max[i] = maxRearrange(A[i]);
            return recurse(A, max, 0, n, 0, k);
        }

        int recurse(int[] A, int[] max, int ai, int n, int sum, int k) {
            if (ai == n)
                return sum;

            int rearrage = -1;
            if (k > 0)
                rearrage = recurse(A, max, ai + 1, n, sum + max[ai], k - 1);

            return Math.max(recurse(A, max, ai + 1, n, sum + A[ai], k), rearrage);
        }

        int maxRearrange(int n) {
            int[] f = new int[10];
            while (n > 0) {
                int d = n % 10;
                ++f[d];
                n /= 10;
            }
            int result = 0;
            for (int i = 9; i >= 0; --i) {
                while (f[i]-- > 0) {
                    result *= 10;
                    result += i;
                }
            }
            return result;
        }
    }

    void main(String[] args) {
        System.out.println(new Solution().maximizeSum(new int[] {123, 45}, 1));
    }
}
