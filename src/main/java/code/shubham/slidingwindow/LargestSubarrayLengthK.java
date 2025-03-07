package code.shubham.slidingwindow;

import java.util.Arrays;

public class LargestSubarrayLengthK {

    class Solution {
        public int[] largestSubarray(int[] A, int k) {
            int maxIndex = 0, lastIndex = A.length - k + 1;
            for (int i = 1; i < lastIndex; ++i)
                if (A[i] > A[maxIndex])
                    maxIndex = i;

            return Arrays.copyOfRange(A, maxIndex, maxIndex + k);
        }

        public int[] largestSubarrayWithDuplicates(int[] A, int k) {
            int maxIndex = 0, lastIndex = A.length - k + 1;
            for (int i = 1, j = 0; i < lastIndex; ++i) {
                if (j != 0)
                    --j;
                while (j < k - 1 && A[i + j] == A[maxIndex + j])
                    ++j;
                if (A[i + j] > A[maxIndex + j])
                    maxIndex = i;
            }
            return Arrays.copyOfRange(A, maxIndex, maxIndex + k);
        }
    }

}
