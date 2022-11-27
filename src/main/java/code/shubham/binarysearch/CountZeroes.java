package code.shubham.binarysearch;

public class CountZeroes {
    int binarySearch(int[] a, int l, int h, int k) {
        if(l <= h) {
            int mid = l + (h - l) / 2;
            if(a[mid] >= k) {
                if ((a[mid] == k && mid > 0 && a[mid-1] != k)
                        || a[mid] == k && mid == 0)
                    return mid;
                return binarySearch(a, l, h - 1, k);
            }
            if (a[mid] < mid)
                return binarySearch(a, l + 1 , h, k);
        }
        return -1;
    }

    int firstOccuranceIdx(int arr[], int k) {
        return binarySearch(arr, 0, arr.length - 1, k);
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
        System.out.println(new CountZeroes().firstOccuranceIdx(a, 0));
    }
}
