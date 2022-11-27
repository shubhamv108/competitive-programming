package code.shubham.arrays;

import java.util.Arrays;

public class SortWaveArrayLexographically {
    public class Solution {
        public int[] wave(int[] A) {
            Arrays.sort(A);
            for (int i = 0; i < A.length; i+=2) {
                if (i > 0 && A[i-1] > A[i]) {
                    swap(A, i-1, i);
                }
                if (i < A.length-1 && A[i] < A[i+1]) {
                    swap(A, i, i+1);
                }
            }
            return A;
        }

        private void swap(int[] A, int i, int j) {
            A[i] = A[i] + A[j];
            A[j] = A[i] - A[j];
            A[i] = A[i] - A[j];
        }
    }
}
