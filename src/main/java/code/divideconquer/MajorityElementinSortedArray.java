package code.divideconquer;

public class MajorityElementinSortedArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 7, 10};
        new Solver().solve(arr);
    }
}

class Solver {

    int binarySearchFirstIdx(int[] a, int l, int h, int k) {
        if(l <= h) {
            int mid = l + (h - l) / 2;
            if(a[mid] >= k) {
                if ((a[mid] == k && mid > 0 && a[mid-1] != k)
                        || a[mid] == k && mid == 0) return mid;
                return binarySearchFirstIdx(a, l, mid - 1, k);
            }
            if (a[mid] < k) return binarySearchFirstIdx(a, l + 1 , h, k);
        }
        return h + 1;
    }

    int binarySearchLastIdx(int[] a, int l, int h, int k) {
        if(l <= h) {
            int mid = l + (h - l) / 2;
            if(a[mid] <= k) {
                if ((a[mid] == k && mid < a.length-1 && a[mid+1] != k)
                        || a[mid] == k && mid == a.length-1) return mid;
                return binarySearchLastIdx(a, l + 1, h, k);
            }
            if (a[mid] > k) return binarySearchLastIdx(a, l , mid - 1, k);
        }
        return l + 1;
    }

    int getFirstIdx(int[] a, int middleIdx)           { return binarySearchFirstIdx(a, 0, middleIdx - 1, a[middleIdx]); }

    int getLastIdx(int[] a, int middleIdx)  { return binarySearchLastIdx(a, middleIdx + 1, a.length - 1, a[middleIdx]); }

    void printMajority(int a[]){
        int middleIdx = a.length/2;
        int firstIdx = getFirstIdx(a, middleIdx);
        int lastIdx  = getLastIdx(a, middleIdx);
        if(lastIdx - firstIdx + 1 > a.length/2) System.out.printf("%d", a[middleIdx]);
        else System.out.printf("%s", "NONE");
    }

    protected void solve(int[] a) {
        printMajority(a);
    }
}
