package code.shubham.binarysearch;

public class MagneticForceBetweenTwoBalls {
    class Solution {
        public int maxDistance(int[] A, int C) {
            int min = (int) 1e9 + 1, max = 0;
            for (int a : A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int l = 1, r = max - min + 1, result = 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (can(A, C, m)) {
                    result = m;
                    l = m + 1;
                } else
                    r = m - 1;
            }

            return result;
        }

        boolean can(int[] A, int C, int d) {
            int p = A[0];
            --C;
            for (int i = 1; i < A.length && C > 0; ++i) {
                if (A[i] - p >= d) {
                    p = A[i];
                    --C;
                }
            }
            return C == 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MagneticForceBetweenTwoBalls().new Solution().maxDistance(new int[] { 5,4,3,2,1,1000000000 }, 2));
    }
}
