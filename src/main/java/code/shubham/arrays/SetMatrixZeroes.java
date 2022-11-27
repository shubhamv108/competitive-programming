package code.shubham.arrays;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

    class Solution {
        public void setZeroes(int[][] matrix) {
            Set<Integer> columns = new HashSet<>();
            Set<Integer> rows = new HashSet<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        columns.add(j);
                        rows.add(i);
                    }
                }
            }
            for (int r : rows) {
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[r][i] = 0;
                }
            }
            for (int c : columns) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][c] = 0;
                }
            }
            matrix = null;
        }
    }

    public static void main(String[] args) {
        new SetMatrixZeroes().new Solution().setZeroes(new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}});
    }

}
