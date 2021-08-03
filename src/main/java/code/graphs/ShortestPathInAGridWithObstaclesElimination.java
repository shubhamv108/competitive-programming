package code.graphs;

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
        public int shortestPath(int[][] grid, int k) {
            if (grid == null || grid.length == 0) return 0;

            Integer[][] visited = new Integer[grid.length][grid[0].length];
            Queue<int[]> q = new LinkedList<>();
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                          // row,col,pathLength,eliminations
            q.offer(new int[] { 0, 0, 0, k });
            while(!q.isEmpty()) {
                int[] t = q.poll();
                int row = t[0];
                int col = t[1];
                if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) continue;
                if (row == grid.length - 1 && col == grid[0].length - 1) return t[2];
                if (grid[row][col] == 1) {
                    if (t[3] > 0) t[3]--;
                    else continue;
                }

                if (visited[row][col] != null && visited[row][col] >= t[3]) continue;
                visited[row][col] = t[3];

                for (int[] dir : directions) {
                    q.offer(new int[] { row + dir[0], col + dir[1], t[2] + 1, t[3] });
                }
            }
            return -1;
        }
    }


    class Solution2 {
        public int shortestPath(int[][] grid, int k) {
            int steps = 0;
            if (grid == null || grid.length == 0) return steps;
            int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
            Integer[][] visited = new Integer[grid.length][grid[0].length];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { 0, 0, 0 });
            visited[0][0] = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] cur = q.poll();
                    if (cur[0] == grid.length - 1 && cur[1] == grid[0].length - 1) return steps;

                    for (int[] dir : directions) {
                        int row = cur[0] + dir[0];
                        int col = cur[1] + dir[1];
                        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) continue;
                        int eliminate = cur[2] + grid[row][col];
                        if ((visited[row][col] != null && eliminate >= visited[row][col]) || eliminate > k) continue;
                        visited[row][col] = cur[2];
                        q.offer(new int[]{ row, col, eliminate });
                    }
                }
                steps++;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ShortestPathInAGridWithObstaclesElimination().new Solution2()
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
