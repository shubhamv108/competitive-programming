package code.shubham.dynamicprogramming;

public class LongestPathInMaze {

    public static void main(String[] args) {
        /**
         * Can go up, right, down
         */
        String[] maze = new String[] {
                "#.#..#",
                "#.#..#",
                "#.##.#",
                "#..#.#",
                "#..#.#",
                "#..#.#"
        };
        System.out.println(new LongestPathInMaze().getLongestPath(maze));
    }

    boolean[][] isLeftInfluenced;
    int getLongestPath(String[] maze) {
        int[][] dp = new int[maze.length][maze[0].length()];
        isLeftInfluenced = new boolean[maze.length][maze[0].length()];
        for (int i = 0; i < maze.length; i++) {
            if (i > 0) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (maze[i].charAt(j) == '.') {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            for (int j = 0; j < maze[0].length(); j++)  {
                if (maze[i].charAt(j) == '.') {
                    if (i > 0) {
                        dp[i][j] = getLongestPathTopLeft(dp, i, j) + 1;
                    } else {
                        dp[i][j] = j > 0 ? dp[i][j - 1] + 1 : 1;
                    }
                }
            }
            int right = 0;
            for (int j = maze.length - 1; j >= 0; j--)  {
                if (maze[i].charAt(j) == '.') {
                    if (i > 0) {
                        if (j < dp[0].length - 1 && !isLeftInfluenced[i][j + 1]) {
                            int temp = getLongestPathRight(dp, i, j);
                            dp[i][j] = temp + 1 > dp[i][j] ? temp + 1 : dp[i][j];
                        } else if (j < dp[0].length - 1 && isLeftInfluenced[i][j + 1]) {
                            dp[i][j] = dp[i-1][j+1] > 0 &&  dp[i-1][j+1] + 2 > dp[i][j] ? dp[i-1][j+1] + 2 : dp[i][j];
                        }
                    } else {
                        right = j < dp[i].length - 1 ? right + 1 : 1;
                        dp[i][j] = right > dp[i][j] ? right : dp[i][j];
                    }
                } else {
                    right = 0;
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        int longest = 0;
        for (int j = 0; j < dp[0].length; j++) {
            longest = dp[maze.length - 1][j] > longest ? dp[maze.length - 1][j] : longest;
        }
        return longest;
    }

    int getLongestPathRight(int[][] dp, int i, int j) {
        int right = 0;
        if (j != dp[i].length - 1) right = dp[i][j + 1];
        return right;
    }

    int getLongestPathTopLeft(int[][] dp, int i, int j) {
        int top = 0, left = 0;
        if (j != 0) left = dp[i][j-1];
        if (i != 0) top = dp[i-1][j];
        isLeftInfluenced[i][j] = left > top;
        return left > top ? left : top;
    }
}
