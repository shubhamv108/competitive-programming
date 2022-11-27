package code.shubham.graphs.dijkastras;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MinimumObstacleRemovalToReachCorner {
    class Solution {
        int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        public int minimumObstacles(int[][] A) {
            int m = A.length, n = A[0].length;
            Integer[][] visited = new Integer[m][n];

            PriorityQueue<int[]> q = new PriorityQueue<>(
                    (x, y) -> visited[x[0]][x[1]] - visited[y[0]][y[1]]);
            q.offer(new int[] { 0, 0 });
            visited[0][0] = A[0][0];
            int p[], r, c;
            while (!q.isEmpty()) {
                p = q.poll();
                if (p[0] == m-1 && p[1] == n-1)
                    return visited[p[0]][p[1]];

                for (int[] dir : dirs) {
                    r = p[0] + dir[0];
                    c = p[1] + dir[1];
                    if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] != null)
                        continue;

                    visited[r][c] = A[r][c] + visited[p[0]][p[1]];
                    q.offer(new int[] {r, c});
                }
            }

            return 0;
        }
    }

    class Solution2 {
        public int minimumObstacles(int[][] A) {
            int m = A.length, n = A[0].length;
            Integer[][] visited = new Integer[m][n];
            int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[] { 0, 0, 0 });
            visited[0][0] = A[0][0];
            int p[], r, c;
            while (!q.isEmpty()) {
                p = q.poll();
                if (p[0] == m-1 && p[1] == n-1)
                    return visited[m-1][n-1];

                for (int[] dir : dirs) {
                    r = p[0] + dir[0];
                    c = p[1] + dir[1];
                    if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] != null)
                        continue;

                    visited[r][c] = A[r][c] + visited[p[0]][p[1]];
                    if (A[r][c] == 1)
                        q.offer(new int[] { r, c });
                    else
                        q.offerFirst(new int[] { r, c });
                }
            }

            return visited[m-1][n-1];
        }
    }
}
