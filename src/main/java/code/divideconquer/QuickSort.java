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


    static class QuickSortSecond {
        void ascending(int[] arr, int low, int high) {
            if (low < high) {
                int partitionIndex = partition(arr, low, high);
                ascending(arr, low, partitionIndex - 1);
                ascending(arr, partitionIndex + 1, high);
            }
        }
        int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j <= high - 1; j++) {
                if (arr[j] < pivot) {
                    i++;
                    if (i < j) {
                        swap(arr, i, j);
                    }
                }
            }
            swap(arr, i+1, high);
            return i + 1;
        }
        void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 8, 0, 56, 89, 9, 78,21,45,23, 9, 76};
//        new QuickSort().quicksortArray(a);
        new QuickSortSecond().ascending(a, 0, a.length - 1);
        Arrays.stream(a).forEach(System.out::println);

    }
}
