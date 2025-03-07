package code.shubham.slidingwindow;

public class MaxConsecutiveOnesIII {

    class Solution {
        public int longestOnes(int[] A, int k) {
            int start = 0, result = 0;
            for (int end = 0; end < A.length; ++end) {
                if (A[end] == 0) {
                    while (k == 0)
                        if (A[start++] == 0)
                            ++k;
                    --k;
                }

                result = Math.max(result, end - start + 1);
            }

            return result;
        }
    }

}
