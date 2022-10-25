package code.arrays;

import java.util.Arrays;

public class ThreeSumClosest {
    class Solution {
        public int threeSumClosest(int[] A, int target) {
            Arrays.sort(A);
            int len = A.length, t, l, r, mid, sum, result = A[0] + A[1] + A[len - 1];

            for (int i = 0; i < len; i++) {
                l = i + 1; r = len - 1;
                while (l < r) {
                    sum = A[i] + A[l] + A[r];

                    if (sum == target)
                        return sum;

                    if (sum < target)
                        l++;
                    else
                        r--;

                    if (Math.abs(target - sum) < Math.abs(result - target))
                        result = sum;
                }
            }

            return result;
        }
    }
}
