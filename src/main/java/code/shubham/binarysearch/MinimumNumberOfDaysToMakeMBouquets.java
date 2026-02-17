package code.shubham.binarysearch;

public class MinimumNumberOfDaysToMakeMBouquets {
    class Solution {
        public int minDays(int[] A, int M, int K) {
            if (A.length < M * K)
                return -1;

            int max = 0;
            for (int a : A)
                max = Math.max(max, a);

            int l = 1, r = max, result = max;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (can(A, M, K, m)) {
                    result = m;
                    r = m - 1;
                } else
                    l =  m + 1;
            }
            return result;
        }

        boolean can(int[] A, int M, int K, int d) {
            int c  = 0, f = 0;
            for (int i = 0; i < A.length; ++i) {
                if (A[i] <= d)
                    ++f;
                else
                    f = 0;

                if (f == K) {
                    ++c;
                    f = 0;
                }

                if (c == M)
                    return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfDaysToMakeMBouquets().new Solution().minDays(new int[] {7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }
}
