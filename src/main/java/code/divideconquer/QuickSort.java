package code.divideconquer;

import java.util.Arrays;

public class QuickSort {

    int partition(int[] a, int l, int r) {
        int pivot = a[(l+r)/2];
        while(l <= r) {
            while(a[l] < pivot) l++;
            while(a[r] > pivot) r--;
            if(l <= r) {
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
                l++;
                r--;
            }
        }
        return l;
    }

    void quickSortHelper(int[] a, int l, int r) {
        int idx = partition(a, l, r);
        if(l < idx - 1)
            quickSortHelper(a, l, idx-1);
        if(idx < r)
            quickSortHelper(a, idx, r);
    }

    void quicksortArray(int[] arr) {
        quickSortHelper(arr, 0, arr.length-1);
    }


    public static void main(String[] args) {
        int[] a = {3, 4, 8, 0, 56, 89, 9, 78,21,45,23, 9, 76};
//        new QuickSort().quickSort(a);
        Arrays.stream(a).forEach(System.out::println);

    }
}
