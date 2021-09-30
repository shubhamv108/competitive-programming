package code.binarysearch;

import java.util.Arrays;

public class MedianMaxSumOfSubArrays {

    class Solution {
        int solve(int[] A, int channels) {
            Arrays.sort(A);
            int l = 0, r = A.length - 1, result = -1;
            while (l <= r) {
                int m = l + (r -l) / 2;
                if (isPossible(A, m, channels)) {
//                    result = get;
                }
            }
            return result;
        }

        boolean isPossible(int[] A, int m, int channels) {
            return false;
        }

        double getMedian(int[] A, int l, int r) {
            int count = (r - l) + 1;
            int m = l + (r - l) / 2;
            if ((count & 1) == 0) {
                return (A[m] + A[m+1]) / 2;
            }
            return m;
        }
    }

}
