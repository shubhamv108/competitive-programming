package code.dynamicprogramming;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 */
public class UniquePathsWithObstacles {

    public int count(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int[] dp = new int[obstacleGrid[0].length];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[obstacleGrid[0].length - 1];
    }

}
