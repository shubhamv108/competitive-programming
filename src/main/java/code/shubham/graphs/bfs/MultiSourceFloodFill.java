package code.shubham.graphs.bfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MultiSourceFloodFill {
    class Solution {
        int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        public int[][] colorGrid(int n, int m, int[][] A) {
            int[][] result = new int[n][m];

            Queue<int[]> q = new LinkedList<>();
            for (int[] a : A) {
                result[a[0]][a[1]] = Math.max(result[a[0]][a[1]], a[2]);
                q.offer(new int[] { a[0], a[1], result[a[0]][a[1]] });
            }

            while (!q.isEmpty()) {
                HashMap<String, Integer> colors = new HashMap<>();
                int size = q.size();
                for (int step = 0; step < size; ++step) {
                    int[] p = q.poll();

                    for (int[] dir : dirs) {
                        int x = p[0] + dir[0];
                        int y = p[1] + dir[1];
                        if (x < 0 || y < 0 || x == n || y == m || result[x][y] != 0)
                            continue;

                        String key = x + "-" + y;
                        colors.put(key, Math.max(colors.getOrDefault(key, 0), p[2]));
                    }
                }

                for (Map.Entry<String, Integer> e : colors.entrySet()) {
                    String[] key = e.getKey().split("-");
                    int x = Integer.parseInt(key[0]);
                    int y = Integer.parseInt(key[1]);
                    result[x][y] = e.getValue();
                    q.offer(new int[] { x, y, e.getValue() });
                }
            }

            return result;
        }
    }

    void main() {
        var solution = new MultiSourceFloodFill().new Solution();
        int[][] R = solution.colorGrid(3, 3, new int[][] {
                {0, 0, 1},
                {2, 2, 2}
        });
        for (int[] r : R)
            System.out.println(Arrays.toString(r));
    }
}
