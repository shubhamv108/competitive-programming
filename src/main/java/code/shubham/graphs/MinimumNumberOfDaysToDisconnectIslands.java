package code.shubham.graphs;

public class MinimumNumberOfDaysToDisconnectIslands {

    class Solution {
        int curColor = 1;
        int[][] directions = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        public int minDays(int[][] grid) {
            int islandCount = countIslands(grid);
            if (islandCount == 0 || islandCount > 1) return 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == curColor) {
                        grid[i][j] = 0;
                        islandCount = countIslands(grid);
                        if (islandCount != 1) return 1;
                        grid[i][j] = curColor;
                    }
                }
            }
            return 2;
        }


        int countIslands(int[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == curColor) {
                        colorIslands(grid, i, j);
                        count++;
                    }
                }
            }
            curColor++;
            return count;
        }

        void colorIslands(int[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != curColor) return;
            grid[i][j]++;
            for (int[] dir : directions) colorIslands(grid, i+dir[0], j+dir[1]);
        }

    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumNumberOfDaysToDisconnectIslands().new Solution().minDays(new int[][] {
                        {0,1,1,0},{0,1,1,0},{0,0,0,0}
                })
        );
    }

}
