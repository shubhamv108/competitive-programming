package code.shubham.slidingwindow;

public class MinimumSizeSubarraySum {

    class Solution {
        public int minSubArrayLen(int t, int[] A) {
            int start = 0, sum = 0, result = 100001;

            for (int end = 0; end < A.length; ++end) {
                sum += A[end];

                while (sum >= t) {
                    result = Math.min(result, end - start + 1);
                    sum -= A[start++];
                }
            }

            return result == 100001 ? 0 : result;
        }
    }

}
