package code.shubham.dynamicprogramming.jumpgame;

public class JumpGameV {
    class Solution {
        public int maxJumps(int[] A, int d) {
            Integer[] dp = new Integer[A.length];
            int result = 0;
            for (int i = 0; i < A.length; i++)
                result = Math.max(result, maxIndices(A, d, i, dp));
            return result;
        }

        int maxIndices(int[] A, int d, int index, Integer[] dp) {
            if (dp[index] != null)
                return dp[index];

            int result = 0;
            for (int x = index+1; x <= (index+d) && x < A.length; x++) {
                if (A[x] >= A[index])
                    break;
                result = Math.max(result, maxIndices(A, d, x, dp));
            }

            for (int x = index-1; x >= (index-d) && x > -1; x--) {
                if (A[x] >= A[index])
                    break;
                result = Math.max(result, maxIndices(A, d, x, dp));
            }

            return dp[index] = 1 + result;
        }
    }
}
