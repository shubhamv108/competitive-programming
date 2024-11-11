package code.shubham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
    class Solution {
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        public List<List<Integer>> pacificAtlantic(int[][] A) {
            List<List<Integer>> result = new ArrayList<>();

            Boolean[][] dp1 = new Boolean[A.length][A[0].length];
            Boolean[][] dp2 = new Boolean[A.length][A[0].length];
            for (int r = 0; r < A.length; ++r)
                for (int c = 0; c < A[r].length; ++c)
                    if (
                        isOCeanReachable(A, r, c, 1000000, -1, -1, dp1, new boolean[A.length][A[0].length])
                                &&
                        isOCeanReachable(A, r, c, 1000000, A.length, A[0].length, dp2, new boolean[A.length][A[0].length])
                    )
                        result.add(Arrays.asList(r, c));

            return result;
        }

        boolean isOCeanReachable(int[][] A, int r, int c, int prevHeight, int oceanR, int oceanC, Boolean[][] dp, boolean[][] visited) {
            if (r == oceanR || c == oceanC)
                return true;

            if (r < 0 || c < 0 || r == A.length || c == A[0].length || visited[r][c] || A[r][c] > prevHeight)
                return false;

            visited[r][c] = true;

//            if (dp[r][c] != null)
//                return dp[r][c];

            for (int[] dir : dirs)
                if (isOCeanReachable(A, r + dir[0], c + dir[1], A[r][c], oceanR, oceanC, dp, visited))
                    return dp[r][c] = true;

            return dp[r][c] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PacificAtlanticWaterFlow()
                        .new Solution()
                        .pacificAtlantic(new int[][] {
                            { 1, 2, 2, 3, 5 },
                            { 3, 2, 3, 4, 4 },
                            { 2, 4, 5, 3, 1 },
                            { 6, 7, 1, 4, 5 }
                        })
        );
    }
}
