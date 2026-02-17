package code.shubham.binarysearch;

public class KthMissingPositiveNumber {
    class Solution {
        public int findKthPositive(int[] A, int k) {
            int l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (A[m] - m - 1 < k)
                    l = m + 1;
                else
                    r = m - 1;
            }

            return l + k;
        }
    }
}
