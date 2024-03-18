package code.shubham.arrays;

public class SubarrayProductLessThanK {
    class Solution {
        public int numSubarrayProductLessThanK(int[] A, int k) {
            if (k <= 1)
                return 0;

            int result = 0;
            long p = 1;
            for (int l = 0, r = 0; r < A.length; ++r) {
                p *= A[r];
                while (p >= k)
                    p /= A[l++];
                result += r - l + 1;
            }
            return result;
        }
    }
}
