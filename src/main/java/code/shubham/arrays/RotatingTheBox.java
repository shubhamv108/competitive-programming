package code.shubham.arrays;

import java.util.Arrays;

public class RotatingTheBox {
    class Solution {

        public char[][] rotateTheBox(char[][] A) {
            int m = A.length, n = A[0].length;
            char[][] result = new char[n][m];

            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    result[i][j] = '.';


            for (int r = 0; r < m; ++r) {
                int lowestObstacle = n;
                for (int c = n-1; c >= 0; --c) {
                    if (A[r][c] == '*') {
                        result[n-c][m-r-1] = A[r][c];
                        lowestObstacle = n-c;
                    } else if (A[r][c] == '#')
                        result[--lowestObstacle][m - r - 1] = '#';
                }
            }

            return result;
        }
    }

    static void main() {
        char[][] solution = new RotatingTheBox().new Solution().rotateTheBox(new char[][] {
            {'#','#','*','.','*','.'},
            {'#','#','#','*','.','.'},
            {'#','#','#','.','#','.'}
        });
        for (char[] a : solution)
            System.out.println(Arrays.toString(a));

    }
}
