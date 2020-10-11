package code.dynamicprogramming;

public class Knapsack01 {

    class Solution {
        int[] A;
        int W;
        Integer[][] dp;
        Solution(int[] weights, int W) {
            this.A = weights;
            this.W = W;
            this.dp = new Integer[weights.length][W+1];
        }

        int solve() {
            return recurse(0, 0);
        }

        int recurse(int startPos, int w) {
            if (w > W) return Integer.MIN_VALUE;
            if (startPos == A.length) return w;
            if (dp[startPos][w] != null) return dp[startPos][w];
            int result = w;
            for (int i = startPos; i < A.length; i++) {
                int r = recurse(i + 1, w + A[i]);
                result = Math.max(r, result);
            }
            return dp[startPos][w] = result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new Knapsack01().new Solution(new int[] {10, 30, 50}, 50).solve()
        );
    }

}
