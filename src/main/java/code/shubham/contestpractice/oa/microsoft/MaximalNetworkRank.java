package code.shubham.contestpractice.oa.microsoft;

public class MaximalNetworkRank {
    class Solution {
        public int maximalNetworkRank(int n, int[][] A) {
            boolean[][] g = new boolean[n][n];
            int[] c = new int[n];
            for (int[] a : A) {
                ++c[a[0]];
                ++c[a[1]];
                g[a[0]][a[1]] = true;
                g[a[1]][a[0]] = true;
            }

            int result = 0, j;
            for (int i = 0; i < n; ++i)
                for (j = i + 1; j < n; ++j)
                    result = Math.max(result, c[i] + c[j] - (g[i][j] ? 1 : 0));
            return result;
        }
    }
}
