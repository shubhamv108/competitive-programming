package code.backtracking;

public class UniquePathsIII {
    class Solution {
        int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        public int uniquePathsIII(int[][] A) {
            int m = A.length, n = A[0].length, emptyCells = 0;
            for (int r = 0; r < m; r++)
                for (int c  = 0; c < n; c++)
                    if (A[r][c] == 0)
                        emptyCells++;

            for (int r = 0; r < m; r++)
                for (int c  = 0; c < n; c++)
                    if (A[r][c] == 1)
                        return find(A, r, c, m, n, emptyCells);
            return 0;
        }

        int find(int[][] A, int r, int c, int m, int n, int emptyCells) {
            if (r < 0 || c < 0 || r >= m || c >= n || A[r][c] == -1)
                return 0;

            if (A[r][c] == 2)
                return emptyCells == -1 ? 1 : 0;

            int count = 0;
            A[r][c] = -1;
            emptyCells--;
            for (int[] dir : dirs)
                count += find(A, r + dir[0], c + dir[1], m, n, emptyCells);
            A[r][c] = 0;

            return count;
        }
    }

    public static void main(String[] args) {
        Solution solution = new UniquePathsIII().new Solution();
        System.out.println(solution.uniquePathsIII(new int[][] {
                {1,  0,  0,  0},
                {0,  0,  0,  0},
                {0,  0,  2, -1}

//                {0,1},{2,0}
        }));
    }
}
