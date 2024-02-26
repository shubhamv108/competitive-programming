package code.shubham.arrays;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

    class Solution {
        public void setZeroes(int[][] A) {
            int m = A.length, n = A[0].length, i, j;

            for (i = 0; i < m; ++i)
                for (j = 0; j < n; ++j)
                    if (A[i][j] == 0) {
                        A[i][0] = 0;
                        A[0][j] = 0;
                    }

            for (i = 1; i < m; ++i)
                if (A[i][0] == 0)
                    for (j = 1; j < n; ++j)
                        A[i][j] = 0;

            for (j = 1; j < n; ++j)
                if (A[0][j] == 0)
                    for (i = 1; i < m; ++i)
                        A[i][j] = 0;

            for (i = 0; i < m; ++i)
                for (j = 0; j < n; ++j)
                    System.out.println(A[i][j]);
        }
    }

    public static void main(String[] args) {
        new SetMatrixZeroes().new Solution().setZeroes(new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}});
    }

}
