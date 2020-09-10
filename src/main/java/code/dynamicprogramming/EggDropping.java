package code.dynamicprogramming;

public class EggDropping {

    /**
     * Time: O(n^2 * k)
     * Space (n * k)
     */
    class Solution {
        /**
         *
         * @param n eggs
         * @param k floors
         * @return
         */
        public int superEggDrop(int n, int k) {
            int F[][] = new int[n+1][k+1];
            int i, j, x;

            for (i = 1; i <= n; i++) {
                F[i][1] = 1;
                F[i][0] = 0;
            }

            for (j = 1; j <= k; j++)
                F[1][j] = j;

            for (i = 2; i <= n; i++) {
                for (j = 2; j <= k; j++) {
                    F[i][j] = Integer.MAX_VALUE;
                    for (x = 1; x <= j; x++)
                        F[i][j] = Math.min(F[i][j], 1 + Math.max(F[i-1][x-1], F[i][j-x]));
                }
            }
            return F[n][k];
        }
    }

    public static void main(String[] args) {
        System.out.println(new EggDropping().new Solution().superEggDrop(7, 10000));
    }
}
