package code.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitInAMaze {
    class Solution {
        public int nearestExit(char[][] maze, int[] entrance) {
            int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            int p[], x, y, rows = maze.length, cols = maze[0].length, result = 0, size;

            Queue<int[]> q = new LinkedList<>();

            q.offer(entrance);
            maze[entrance[0]][entrance[1]] = '+';

            while (!q.isEmpty()) {
                size = q.size();
                for (int i = 0; i < size; i++) {
                    p = q.poll();
                    for (int[] dir : dirs) {
                        x = p[0] + dir[0];
                        y = p[1] + dir[1];

                        if (isNotValid(maze, rows, cols, x, y))
                            continue;
                        if (isExit(rows, cols, x, y))
                            return result + 1;

                        q.offer(new int[] {x, y});
                        maze[x][y] = '+';
                    }
                }
                result++;
            }

            return -1;
        }

        boolean isExit(int rows, int cols, int r, int c) {
            return r == 0 || c == 0 || r == rows - 1 || c == cols - 1;
        }

        boolean isNotValid(char[][] A, int rows, int cols, int r, int c) {
            return r < 0 || c < 0 || r == rows || c == cols || A[r][c] == '+';
        }
    }

    public static void main(String[] args) {
        char[][] maze = {
                {'+','.','+','+','+','+','+'},
                {'+','.','+','.','.','.','+'},
                {'+','.','+','.','+','.','+'},
                {'+','.','.','.','+','.','+'},
                {'+','+','+','+','+','.','+'}};
        int[] entrance = {0,1};
        System.out.println(
                new NearestExitInAMaze().new Solution().nearestExit(maze, entrance)
        );
    }
}
