package code.shubham.binarysearch;

public class FindIndex {


    int binarySearch(int[] a, int l, int h) {
        if(l <= h) {
            int mid = l + (h - l) / 2;
            if (a[mid] == mid)
                return mid;
            if (a[mid] < mid)
                return binarySearch(a, l + 1, h);
            if (a[mid] > mid)
                return binarySearch(a, l, h - 1);
        }
        return -1;
    }

    int findingIdx(int arr[]) {
        return binarySearch(arr, 0, arr.length - 1);
    }


    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 2, 4, 6, 9, 11, 12, 13, 14};
        System.out.println(new FindIndex().findingIdx(a));
    }
}
