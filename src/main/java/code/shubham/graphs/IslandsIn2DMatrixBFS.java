package code.shubham.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Time Complexity: O(V+E)
 */
public class IslandsIn2DMatrixBFS {

    private class Pos {
        private int i;
        private int j;
        private Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private class Solution {
        private int[][] arr;
        private int VALID_LAND = 1;
        private final int VISITED = Integer.MIN_VALUE;
        private Solution(int[][] arr) {
            this.arr = arr;
        }
        private Solution(int[][] arr, int validLand) {
            this.arr = arr;
            this.VALID_LAND = validLand;
        }

        private int get() {
            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (isSafe(i, j)) {
                        bfs(i, j);
                        result++;
                    }
                }
            }
            return result;
        }

        private void bfs(int I, int J) {
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(I, J));
            arr[I][J] = VISITED;
            Pos t;
            while(!q.isEmpty()) {
                t = q.poll();
                for (int i = t.i - 1; i <= t.i + 1; i++) {
                    for (int j = t.j - 1; j <= t.j + 1; j++) {
                        if (i == t.i && j == t.j)
                            continue;
                        if (isSafe(i, j))
                            q.offer(new Pos(i, j));
                    }
                }
                arr[t.i][t.j] = VISITED;
            }
        }

        public boolean isSafe(int i, int j) {
            return i > -1 && i < arr.length && j > -1 && j < arr[i].length && !isVisted(i, j) && isValid(i, j);
        }

        public boolean isVisted(int i, int j) {
            return arr[i][j] == VISITED;
        }

        public boolean isValid(int i, int j) {
            return arr[i][j] == VALID_LAND;
        }

    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 1, 0, 0, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 1, 0, 1, 0, 1 }
        };
        int validLand = 1;
        System.out.println(
                new IslandsIn2DMatrixBFS().new Solution(arr, validLand).get()
        ); // 5
    }

}
