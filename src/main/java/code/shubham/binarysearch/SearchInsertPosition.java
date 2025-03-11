package code.shubham.binarysearch;

public class SearchInsertPosition {
    class Solution {
        public int searchInsert(int[] A, int target) {
            int l = 0, r = A.length - 1, m;

            while (l <= r) {
                m = l + (r - l) / 2;
                if (A[m] == target)
                    return m;

                if (target < A[m])
                    r = m - 1;
                else
                    l = m + 1;
            }
            return l;
        }
    }
}
