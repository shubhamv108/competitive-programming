package code.shubham.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle).
 * In one step, you can move up, down, left or right from and to an empty cell.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Solution: BFS + DP (visited)
 *
 * Time Complexity: O(rowsCount(m) * columnCount(n) * maxNumberOfEliminations(k))
 */
public class ShortestPathInAGridWithObstaclesElimination {

    class Solution {
        public int shortestPath(int[][] A, int k) {
            if (A.length == 1 && A[0].length == 1)
                return k >= A[0][0] ? 0 : -1;

            int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int steps = 0, m = A.length - 1, n = A[0].length - 1;

            Integer[][] visited = new Integer[m+1][n+1];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0, 0});
            k -= A[0][0];

            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] p = q.poll();

                    for (int[] dir : dirs) {
                        int r = p[0] + dir[0];
                        int c = p[1] + dir[1];
                        if (r < 0 || c < 0 || r > m || c > n || (visited[r][c] != null && visited[r][c] <= p[2]))
                            continue;
                        if (r == m && c == n)
                            return steps + 1;
                        int b = p[2] + A[r][c];
                        if (b > k)
                            continue;
                        q.offer(new int[] {r, c, b});
                        visited[r][c] = p[2];
                    }
                }
                ++steps;
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ShortestPathInAGridWithObstaclesElimination().new Solution()
                        .shortestPath(
                                new int[][] {
                                        { 0, 1, 1 },
                                        { 1, 1, 1 },
                                        { 1, 0, 0 }
                                }, 1
                        )
        );
    }

}
