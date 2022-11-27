package code.shubham.graphs.bfs;

import java.util.ArrayList;
import java.util.Arrays;

public class MinSumPathInMatrix {

    // preorder + dynammic programming (4 directions)
    class Solution {
        ArrayList<Integer> result = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;
        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        ArrayList<Integer> solve(int[][] matrix) {
            Integer[][] visited = new Integer[matrix.length][matrix[0].length];
            visit(matrix, 0, 0 , 0, new ArrayList<>(), visited);
            return result;
        }

        void visit(int[][] A, int r, int c, int curSum, ArrayList<Integer> l, Integer[][] visited) {
            if (r < 0 || c < 0 || r == A.length || c == A[0].length || curSum > minSum) return;
            curSum += A[r][c];
            if (visited[r][c] != null && visited[r][c] < curSum)
                return;
            visited[r][c] = curSum;
            l.add(A[r][c]);
            if (r == A.length - 1 && c == A[0].length - 1 && curSum <= minSum) {
                result.clear();
                result.addAll(l);
                minSum = curSum;
                l.remove(l.size() - 1);
                return;
            }
            for (int[] dir : directions)
                visit(A, r + dir[0], c + dir[1], curSum, l, visited);
            l.remove(l.size() - 1);
        }
    }


    // DP
    class Solution2 {
        int[] getMinCostPath(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            int[][] dp = new int[R][C];
            dp[0][0] = matrix[0][0];
            for (int i = 1; i < C; i++)
                dp[0][i] = dp[0][i-1] + matrix[0][i];
            for (int i = 1; i < R; i++)
                dp[i][0] = dp[i-1][0] + matrix[i][0];
            for (int i = 1; i < R; i++)
                for (int j = 1; j < C; j++)
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];

            int[] result = new int[R+C-1];
            result[0] = matrix[0][0];
            int r = R-1, c = C-1, i = R+C-2;
            while (!(r == 0 && c == 0)) {
                result[i--] = matrix[r][c];
                if (r == 0) c--;
                else if (c == 0) r--;
                else if (dp[r-1][c] < dp[r][c-1]) {
                    r--;
                } else {
                    c--;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // 9526653340
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}
        };

        Arrays.stream(
                new MinSumPathInMatrix()
                    .new Solution2()
                        .getMinCostPath(matrix)
        ).forEach(e -> System.out.println(e + " "));
    }

}
