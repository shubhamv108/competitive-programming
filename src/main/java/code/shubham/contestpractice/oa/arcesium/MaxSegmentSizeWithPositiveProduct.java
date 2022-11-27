package code.shubham.contestpractice.oa.arcesium;

public class MaxSegmentSizeWithPositiveProduct {
    class Solution {
        int solve(int[] A) {
            int n = A.length;
            int negStart = -1, negCount = 0, zeroStart = -1, result = 0;

            for (int i = 0; i < n; i++) {
                if (A[i] < 0) {
                    negCount++;
                    if (negStart == -1)
                        negStart = i;
                }

                if (A[i] == 0) {
                    zeroStart = i;
                    negCount = 0;
                    negStart = -1;
                } else {
                    if ((negCount & 1) == 0)
                        result = Math.max(result, i - zeroStart);
                    else
                        result = Math.max(result, i - negStart);
                }
            }
            return result;
        }
    }
}
