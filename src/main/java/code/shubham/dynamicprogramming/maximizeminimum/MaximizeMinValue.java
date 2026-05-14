package code.shubham.dynamicprogramming.maximizeminimum;

import java.util.Arrays;

public class MaximizeMinValue {

    public class Solution {

        public static int maximiseMinValue(int[] firstShop, int[] secondShop, int k) {
            int n = firstShop.length, result = 0;;
            int maxA = 50 * k;

            // dp[j][a] = max sumB
            int[][] dp = new int[k + 1][maxA + 1];

            // initialize as unreachable
            for (int i = 0; i <= k; ++i)
                Arrays.fill(dp[i], -1);

            dp[0][0] = 0;
            for (int i = 0; i < n; ++i) {
                int A = firstShop[i];
                int B = secondShop[i];

                // iterate backwards to avoid reuse
                for (int j = k - 1; j >= 0; --j)
                    for (int a = 0; a <= maxA - A; ++a)
                        if (dp[j][a] != -1)
                            dp[j + 1][a + A] = Math.max(dp[j + 1][a + A], dp[j][a] + B);
            }

            for (int a = 0; a <= maxA; ++a)
                if (dp[k][a] != -1)
                    result = Math.max(result, Math.min(a, dp[k][a]));

            return result;
        }

    }

    static void main(String[] args) {
        System.out.println(new MaximizeMinValue().new Solution()
                                   .maximiseMinValue(new int[] {6, 3, 6, 5, 1}, new int[] {1, 4, 5, 9, 2}, 3)); // 15
    }
}
