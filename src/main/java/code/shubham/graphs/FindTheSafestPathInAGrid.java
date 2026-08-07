package code.shubham.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindTheSafestPathInAGrid {
    class Solution {
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        public int maximumSafenessFactor(List<List<Integer>> A) {
            int m = A.size(), n = A.get(0).size();

            int[][] dist = new int[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int r = 0; r < m; ++r) {
                for (int c = 0; c < n; ++c) {
                    if (A.get(r).get(c) == 1) {
                        dist[r][c] = 0;
                        q.offer(new int[] { r, c });
                    } else {
                        dist[r][c] = -1;
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] p = q.poll();
                for (int[] dir : dirs) {
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || dist[x][y] != -1)
                        continue;
                    dist[x][y] = dist[p[0]][p[1]] + 1;
                    q.offer(new int[] { x, y });
                }
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> b[2] - a[2]);
            boolean[][] vis = new boolean[m][n];
            pq.offer(new int[] { 0, 0, dist[0][0] });
            while (!pq.isEmpty()) {
                int[] p = pq.poll();
                int r = p[0];
                int c = p[1];
                int safe = p[2];
                if (vis[r][c])
                    continue;

                vis[r][c] = true;
                if (r == m-1 && c == n-1)
                    return safe;

                for (int[] d : dirs) {
                    int x = r + d[0];
                    int y = c + d[1];
                    if (x < 0 || y < 0 || x == m || y == n || vis[x][y])
                        continue;

                    pq.offer(new int[] { x, y, Math.min(safe, dist[x][y]) });
                }
            }
            return 0;
        }
    }

    void main() {
        System.out.println(new Solution().maximumSafenessFactor(new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0,0,0,1)),
            new ArrayList<>(Arrays.asList(0,0,0,0)),
            new ArrayList<>(Arrays.asList(0,0,0,0)),
            new ArrayList<>(Arrays.asList(1,0,0,0))))));
    }
}
