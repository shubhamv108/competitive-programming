package code.shubham.binarysearch;

public class FindMinimumInRotatedSortedArrayII {

    class Solution {
        public int findMin(int[] A) {
            int l = 0, r = A.length - 1;
            while (l < r) {
                int m = l + ((r - l) >> 1);
                if (m > 0 && A[m - 1] > A[m])
                    return A[m];

                if (A[m] > A[r])
                    l = m + 1;
                else if (A[m] < A[r])
                    r = m;
                else
                    --r;
            }

            return A[l];
        }
    }

}
