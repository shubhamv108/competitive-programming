package code.shubham.binarysearch;

public class FindMinimumInRotatedSortedArray {

    class Solution {
        public int findMin(int[] A) {
            int l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (m > 0 && A[m] < A[m - 1])
                    return A[m];

                if (A[m] > A[A.length - 1])
                    l = m + 1;
                else
                    r = m - 1;
            }

            return A[l];
        }
    }

}
