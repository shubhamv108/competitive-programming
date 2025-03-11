package code.shubham.binarysearch;

public class FindSmallestLetterGreaterThanTarget {
    class Solution {
        public char nextGreatestLetter(char[] A, char t) {
            int l = 0, r = A.length - 1, m;

            while (l <= r) {
                m = l + (r - l) / 2;
                if (A[m] <= t)
                    l = m + 1;
                else
                    r = m - 1;
            }

            return l == A.length ? A[0] : A[l];
        }
    }
}
