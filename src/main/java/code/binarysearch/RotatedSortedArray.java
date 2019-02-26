package code.binarysearch;

public class RotatedSortedArray {

    private static int[] a;
    private static int k;

    private static int findPos () {
        int pivot = findPivotAndGet(0, a.length - 1);
        if (pivot == -1) return search (0, a.length - 1);
        if (k == a[pivot]) return pivot;
        if (k < a[pivot] && k > a[a.length - 1]) {
            return search (0, pivot - 1);
        } else {
            return search (pivot + 1 , a.length - 1);
        }
    }

    private static int search (int l, int r) {
        if (r < l) return -1;
        int m = (l+r)/2;
        if (a[m] == k) return m;
        if (k > a[m]) return search(m+1, r);
        return search(l, m-1);
    }

    private static int findPivotAndGet (int l, int r) {
        if (r < l) return -1;
        if (r == l) return l;
        int m = (r+l)/2;
        if (a[m] > a[m+1]) return m;
        if (a[m] > a[r])
            return findPivotAndGet(m+1, r);
        return findPivotAndGet(l, m-1);
    }

    public static void main(String[] args) {
       int[] a = {5, 6, 7, 8, 9, 10, 1, 2, 3};
       RotatedSortedArray.a = a;
       k = 3;
       System.out.println(findPos() + 1);
    }

}
