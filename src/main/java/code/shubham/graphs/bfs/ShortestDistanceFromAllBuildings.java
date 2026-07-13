package code.shubham.graphs.bfs;

import java.util.LinkedList;

public class ShortestDistanceFromAllBuildings {
    class Solution {
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public int shortestDistance(int[][] A) {
            int m = A.length, n = A[0].length, result = 0;

            int[][] total = new int[m][n];
            int emptyLand = 0;

            LinkedList<int[]> q = new LinkedList<>();
            for (int r = 0; r < m; ++r) {
                for (int c = 0; c < n; ++c) {
                    if (A[r][c] != 1)
                        continue;

                    result = Integer.MAX_VALUE;
                    int steps = 0;

                    q.clear();
                    q.offer(new int[] { r, c });
                    while (!q.isEmpty()) {
                        ++steps;
                        int size = q.size();
                        for (int i = 0; i < size; ++i) {
                            int[] p = q.poll();
                            for (int[] dir : dirs) {
                                int x = p[0] + dir[0];
                                int y = p[1] + dir[1];

                                if (x < 0 || y < 0 || x == m || y == n || A[x][y] != emptyLand)
                                    continue;

                                --A[x][y];
                                total[x][y] += steps;
                                result = Math.min(result, total[x][y]);

                                q.offer(new int[] { x, y });
                            }
                        }
                    }
                    --emptyLand;
                }
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }
}
