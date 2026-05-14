package code.shubham.dynamicprogramming.jumpgame;

public class JumpGameIX {
    class Solution {
        public int[] maxValue(int[] A) {
            int n = A.length, min = Integer.MAX_VALUE;
            int[] result = new int[n];
            result[0] = A[0];
            for (int i = 1; i < n; ++i)
                result[i] = Math.max(result[i - 1], A[i]);

            for (int i = n - 2; i >= 0; --i) {
                min = Math.min(min, A[i + 1]);
                if (min < result[i])
                    result[i] = result[i + 1];
            }

            return result;
        }
    }
}
