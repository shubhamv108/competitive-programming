package code.binarysearch;

import java.util.Arrays;

/**
 * ToDo
 */
public class MatrixMedian {
    public class Solution {
        public int findMedian(int[][] A) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                min = Math.min(min, A[i][0]);
                max = Math.max(max, A[i][A[i].length - 1]);
            }
            int medianCount = (A.length * (A[0].length + 1))/2;
            int index = 0;
            int count = 0;
            while (max > min) {
                int mid = min + (max - min) / 2;
                for (int i = 0; i < A.length; i++) {
                    index = Arrays.binarySearch(A[i], mid);
                    if (index < 0) index = Math.abs(index) - 1;
                    else while (index < A[i].length && A[i][index] == mid) index++;
                    count += index;
                }
                if (count < medianCount) {
                    min = mid + 1;
                } else {
                    max = mid;
                }
            }
            return min;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new MatrixMedian().new Solution().findMedian(
                    new int[][] {
                            {1, 3, 5},
                            {2, 6, 9},
                            {3, 6, 9}
                    }
            )
        );
    }
}
