package code.shubham.graphs.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfDistinctIslands {
    class Solution {
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int countDistinctIslands(int[][] A) {
            int m = A.length, n = A[0].length;
            HashSet<String> set = new HashSet<>();
            for (int r = 0; r < m; ++r)
                for (int c = 0; c < n; ++c)
                    if (A[r][c] == 1)
                        set.add(bfs(A, r, c, m, n));
            return set.size();
        }

        String bfs(int[][] A, int r, int c, int m, int n) {
            StringBuilder s = new StringBuilder();

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {r, c});
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] p = q.poll();
                    for (int[] dir : dirs) {
                        int x = p[0] + dir[0], y = p[1] + dir[1];
                        if (x < 0 || y < 0 || x == m || y == n || A[x][y] != 1) {
                            s.append(0);
                            continue;
                        }
                        if (A[x][y] == 1) {
                            A[x][y] = 2;
                            q.offer(new int[] { x, y });
                            s.append(1);
                        }

                    }
                }
            }
            return s.toString();
        }
    }

    public class Solution2 {
        /**
         * @param grid: the 2D grid
         * @return: the number of distinct islands
         */

        int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public int numberOfDistinctIslands(int[][] A) {
            HashSet<String> result = new HashSet<>();

            for (int r = 0; r < A.length; ++r)
                for (int c  = 0; c < A[0].length; ++c)
                    if (A[r][c] == 1) {
                        StringBuilder island = new StringBuilder();
                        visit(A, r, c, island);
                        result.add(island.toString());
                    }

            return result.size();
        }

        void visit(int[][] A, int r, int c, StringBuilder s) {
            if (r < 0 || c < 0 || r == A.length || c == A[0].length || A[r][c] != 1) {
                s.append(0);
                return;
            }

            A[r][c] = 0;
            s.append(1);

            for (int[] dir : dirs)
                visit(A, r + dir[0], c + dir[1], s);
        }
    }

}
