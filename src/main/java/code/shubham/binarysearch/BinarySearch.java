package code.shubham.binarysearch;

public class BinarySearch {

    class Solution {
        public int search(int[] A, int target) {
            int l = 0, r = A.length - 1, m;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (A[m] == target)
                    return m;
                if (A[m] > target)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return -1;
        }
    }

}
