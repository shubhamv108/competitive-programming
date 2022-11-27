package code.shubham.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class NextPermutation {
    class Solution {
        public void nextPermutation(int[] A) {
            int len = A.length, i = len - 2;
            for (i = len - 2; i > -1; i--) {
                if (A[i] < A[i+1]) {
                    for (int j = len - 1; j > i; j--)
                        if (A[j] > A[i]) {
                            swap(A, i, j);
                            break;
                        }
                    break;
                }
            }
            reverse(A, i+1, len - 1);
        }

        void swap(int[] A, int x, int y) {
            int t = A[x];
            A[x] = A[y];
            A[y] = t;
                Arrays.stream(A).map(e -> (Integer)e).collect(HashSet::new, HashSet::add, HashSet::addAll);
        }

        void reverse(int[] A, int l, int r) {
            while (l < r)
                swap(A, l++, r--);
        }
    }
}
