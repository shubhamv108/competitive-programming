package code.shubham.arrays.sorting;

import java.util.Arrays;

public class WiggleSort2 {
    public class Solution {
        public void wiggleSort(int[] A) {
            int[] temp = A.clone();
            Arrays.sort(temp);
            int n = A.length;
            int left = (n-1)/2;
            int right = n-1;

            int index = 0;
            while (left >= 0 && right >= (n+1)/2) {
                A[index++] = temp[left--];
                A[index++] = temp[right--];
            }
            if (left >= 0) {
                A[index] = temp[left];
            }
        }
    }
}
