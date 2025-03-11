package code.shubham.binarysearch;

public class SearchInRotatedSortedArrayII {

    class Solution {
        public boolean search(int[] A, int t) {
            int l = 0, r = A.length - 1, m = -1;

            while(l <= r) {
                m = l + ((r - l) >> 1);

                if (A[m] == t)
                    return true;

                if (A[m] < A[r] || A[m] < A[l]) {
                    if (t > A[m] && t <= A[r])
                        l = m + 1;
                    else
                        r = m - 1;
                } else if (A[l] < A[m] || A[r] < A[m]) {
                    if (t < A[m] && t >= A[l])
                        r = m - 1;
                    else
                        l = m + 1;
                } else
                    r--;
            }

            return false;
        }
    }

}
