package code.shubham.twopointers;

public class CelebrityProblem {

    class Solution {
        int solve(int[][] A) {
            int c = 0, r = A.length - 1;
            while (c < r) {
                if (A[r][c] == 1)
                    r--;
                else
                    c++;
            }

            int candidate = c;

            for (int i = 0; i < A.length - 1; i++) {
                if (i != candidate && (A[i][candidate] == 0 || A[candidate][i] == 1))
                    return -1;
            }
            return candidate;
        }
    }
}
