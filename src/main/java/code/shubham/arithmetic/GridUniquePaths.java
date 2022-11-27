package code.shubham.arithmetic;

public class GridUniquePaths {
    public class Solution {
        public int uniquePaths(int A, int B) {
            if (A == 1 || B== 1) return 1;
            int[] a = new int[B];
            for (int i = 0; i < A; i++) {
                for (int j = 0; j < B; j++) {
                    if (i == 0 || j == 0) {
                        a[j] = 1;
                    } else {
                        a[j] += a[j-1];
                    }
                }
            }
            return a[B-1];
        }
    }

}
