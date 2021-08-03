package code.graphs.color;

import java.util.HashMap;
import java.util.HashSet;

public class MakingALargeIsland {

    class Solution {
        int[][] directions = new int[][] { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        public int largestIsland(int[][] grid) {
            HashMap<Integer, Integer> color = new HashMap<>();
            int curColor = 2;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        color.put(curColor, paint(grid, i, j, curColor));
                        curColor++;
                    }
                }
            }

            if (curColor == 2) return 1;

            int size = color.get(2);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        int curSize = 1;
                        HashSet<Integer> colors = new HashSet<>();
                        for (int[] dir : directions) {
                            int x = i + dir[0], y = j + dir[1];
                            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) continue;
                            colors.add(grid[x][y]);
                        }
                        for (int c : colors) curSize += color.get(c);
                        size = Math.max(size, curSize);
                    }
                }
            }
            return size;
        }

        int paint(int[][] grid, int i, int j, int color) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) return 0;
            grid[i][j] = color;
            int size = 1;
            for (int[] dir : directions) {
                size +=  paint(grid, i + dir[0], j + dir[1], color);
            }
            return size;
        }

    }

    public static void main(String[] args) {
        System.out.println(
            new MakingALargeIsland().new Solution().largestIsland(new int[][]
                    {
                            {1, 1},
                            {1, 0}
                    }
            )
        );
    }

}
