package code.shubham.binarysearch;

public class SearchInRotatedSortedArray {

    // find which side landed on basis of last element, then condition to go for other side
    class Solution {
        public int search(int[] A, int t) {
            int l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (A[m] == t)
                    return m;
                if (A[m] > A[A.length-1]) {
                    if (t < A[0] || t > A[m])
                        l = m + 1;
                    else
                        r = m - 1;
                } else {
                    if (t > A[A.length - 1] || t < A[m])
                        r = m - 1;
                    else
                        l = m + 1;
                }
            }
            return -1;
        }
    }

}
