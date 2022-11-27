package code.shubham.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDistToReachObstacle {

    class Solution {
        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int minDist(int[][] A) {
            int n = A.length, m = A[0].length;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { 0, 0 });
            int dist = 0;
            boolean[][] visited = new boolean[n][m];
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] p = q.poll();
                    for (int[] direction : this.directions) {
                        int r = p[0] + direction[0];
                        int c = p[1] + direction[1];
                        if (A[r][c] == 9) return dist+1;
                        if (r < 0 || c < 0 || r == n || r == m || visited[r][c]) continue;
                        visited[r][c] = true;
                        q.offer(new int[] {r, c});
                    }
                }
                dist++;
            }
            return dist;
        }
    }
}
