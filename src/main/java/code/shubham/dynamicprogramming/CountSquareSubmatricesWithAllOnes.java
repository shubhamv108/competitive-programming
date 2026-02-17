package code.shubham.dynamicprogramming;

public class CountSquareSubmatricesWithAllOnes {
    class Solution {
        public int countSquares(int[][] A) {
            int result = 0, r = A.length, c = A[0].length;
            for (int i = 0; i < r; ++i)
                for (int j = 0; j < c; ++j) {
                    if (A[i][j] == 0)
                        continue;
                    if (i > 0 && j > 0)
                        A[i][j] += Math.min(A[i-1][j-1], Math.min(A[i-1][j], A[i][j-1]));
                    result += A[i][j];
                }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CountSquareSubmatricesWithAllOnes().new Solution().countSquares(new int[][]{
                        {0, 1, 1, 1},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1}
                })
        );
    }
}
