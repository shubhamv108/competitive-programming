package code.shubham.arrays;

public class SortByParity {
    class Solution {
        public int[] sortArrayByParity(int[] A) {
            for (int i = 0, j = A.length -1; i < j; ++i, --j) {
                if ((A[i] & 1) == 1) {
                    int t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                }
            }
            return A;
        }
    }
}
