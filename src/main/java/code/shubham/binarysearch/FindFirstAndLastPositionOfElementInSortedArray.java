package code.shubham.binarysearch;

public class FindFirstAndLastPositionOfElementInSortedArray {
    class Solution {
        public int[] searchRange(int[] A, int target) {
            return new int[] { first(A, target), last(A, target) };
        }

        int first(int[] A, int target) {
            int index = -1, l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (A[m] == target)
                    index = m;

                if (A[m] >= target)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return index;
        }

        int last(int[] A, int target) {
            int index = -1, l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (A[m] == target)
                    index = m;

                if (A[m] <= target)
                    l = m + 1;
                else
                    r = m - 1;
            }
            return index;
        }
    }

    class Solution2 {
        public int[] searchRange(int[] A, int target) {
            return new int[] { s(A, target, true), s(A, target, false) };
        }

        int s(int[] A, int target, boolean f) {
            int index = -1, l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (A[m] == target)
                    index = m;

                if (A[m] > target)
                    r = m - 1;
                else if (A[m] < target)
                    l = m + 1;
                else {
                    if (f)
                        r = m - 1;
                    else
                        l = m + 1;
                }
            }
            return index;
        }
    }
}
