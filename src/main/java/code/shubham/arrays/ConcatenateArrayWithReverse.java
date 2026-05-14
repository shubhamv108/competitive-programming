package code.shubham.arrays;

import java.util.Arrays;

public class ConcatenateArrayWithReverse {
    class Solution {
        public int[] concatWithReverse(int[] A) {
            int n = A.length;
            int[] result = new int[2*n];
            int ri = 0;
            for (int i = 0; i < n; ++i)
                result[ri++] = A[i];
            for (int i = n - 1; i >= 0; --i)
                result[ri++] = A[i];

            return result;
        }
    }

    void main() {
        System.out.println(Arrays.toString(new ConcatenateArrayWithReverse().new Solution().concatWithReverse(new int[] { 1, 2, 3 })));
    }
}
