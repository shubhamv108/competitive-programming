package code.arrays.sorting;

import java.util.Arrays;

public class SelectionSort {

    class Solution {
        void sort(int[] A) {
            int i, j, len = A.length, min;

            for (i = 0; i < len - 1; i++) {
                min = i;
                for (j = i + 1; j < len; j++) {
                    if (A[j] < A[min]) {
                        min = j;
                        swap(A, min, i);
                    }
                }
            }
        }

        void swap(int[] A, int x, int y) {
            int t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 4, 1, 45, 63434, 321};
        new SelectionSort().new Solution().sort(A);
        Arrays.stream(A).forEach(System.out::println);
    }

}
