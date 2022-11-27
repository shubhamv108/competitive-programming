package code.shubham.binarysearch;

public class RotatedSortedArray {

    private class SolutionPivot {

        private int[] a;
        private int k;

        private SolutionPivot(int[] a, int k) {
            this.a = a;
            this.k = k;
        }

        private int findPos () {
            int pivot = findPivotAndGet(0, a.length - 1);
            if (pivot == -1) return search (0, a.length - 1);
            if (k == a[pivot]) return pivot;
            if (k < a[pivot] && k > a[a.length - 1]) {
                return search (0, pivot - 1);
            } else {
                return search (pivot + 1 , a.length - 1);
            }
        }

        private int search (int l, int r) {
            if (r < l) return -1;
            int m = (l+r)/2;
            if (a[m] == k) return m;
            if (k > a[m]) return search(m+1, r);
            return search(l, m-1);
        }

        private int findPivotAndGet (int l, int r) {
            if (r < l) return -1;
            if (r == l) return l;
            int m = (r+l)/2;
            if (a[m] > a[m+1]) return m;
            if (a[m] > a[r])
                return findPivotAndGet(m+1, r);
            return findPivotAndGet(l, m-1);
        }
    }

    private class Solution {

        private int[] A;
        private int k;

        private Solution(int[] A, int k) {
            this.A = A;
            this.k = k;
        }

        private int findPos() {
            if (A == null || A.length == 0) return -1;
            return fidPosIterative(0, A.length - 1);
        }

        private int findPosRecurse(int l, int r) {
            if (l > r) return -1;
            int mid = (l+r)/2;
            if (A[mid] == k) return mid;
            if (A[l] <= A[mid]) {
                if (k >= A[l] && k < A[mid])
                    return findPosRecurse(l, mid-1);
                return findPosRecurse(mid+1, r);
            }
            if (k > A[mid] && k <= A[r])
                return findPosRecurse(mid+1, r);
            return findPosRecurse(l, mid-1);
        }

        private int fidPosIterative(int l, int r) {
            int returnedPosition = -1;
            while (l <= r) {
                int mid = (l+r)/2;
                if (A[mid] == k) returnedPosition = mid;
                if (A[l] <= A[mid]) {
                    if (k >= A[l] && k < A[mid]) {
                        r = mid - 1;
                    }
                    l = mid + 1;
                } else {
                    if (k > A[mid] && k <= A[r]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
            return returnedPosition;
        }

    }

    public static void main(String[] args) {
       int[] a = {5, 6, 7, 8, 9, 10, 1, 2, 3, 4};
       int k = 3;
       System.out.println(new RotatedSortedArray().new Solution(a, k).findPos() + 1);
    }

}
