package code.shubham.slidingwindow;

public class MaximumAverageSubarrayI {

    class Solution {
        public double findMaxAverage(int[] A, int k) {
            int s = 0;
            int i = 0;
            for (; i < k; ++i)
                s += A[i];

            double max = ((double) s) / k;

            for (; i < A.length; ++i) {
                s -= A[i-k];
                s += A[i];
                max = Math.max(max, ((double)s)/k);
            }

            return max;
        }
    }

}
