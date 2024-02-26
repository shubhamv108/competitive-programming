package code.shubham.arrays;

import java.util.Arrays;

public class SumOfAbsoluteDifferencesInASortedArray {
    class Solution {
        public int[] getSumAbsoluteDifferences(int[] A) {
            int l = 0, r = 0, n = A.length;
            int[] result = new int[n];
            for (int a : A)
                r += a;

            for (int i = 0; i < n; ++i) {
                result[i] = r - l + (i * A[i]) - ((n - i) * A[i]);
                l += A[i];
                r -= A[i];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(new SumOfAbsoluteDifferencesInASortedArray().new Solution().getSumAbsoluteDifferences(
                new int[] {2, 3, 5}
        ));
    }
}
