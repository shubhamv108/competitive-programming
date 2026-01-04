package code.shubham.binarysearch;

public class BinarySearch {

    class Solution {
        public int exists(int[] A, int target) {
            int l = 0, r = A.length - 1, m;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (A[m] == target)
                    return m;
                if (A[m] > target)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return -1;
        }

        int firstIndex(int[] A, int T) {
            int l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (A[m] == T && m > 0 && A[m-1] < T)
                    return m;
                if (A[m] < T)
                    l = m + 1;
                else
                    r = m - 1;
            }
            return l;
        }

        int lastIndex(int[] A, int T) {
            int last = A.length - 1;
            int l = 0, r = last;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (A[m] == T && m < last && A[m+1] > T)
                    return m;
                if (A[m] > T)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return r;
        }
    }

}
