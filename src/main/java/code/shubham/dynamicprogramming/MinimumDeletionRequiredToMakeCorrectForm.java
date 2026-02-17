package code.shubham.dynamicprogramming;

public class MinimumDeletionRequiredToMakeCorrectForm {
    class Solution {
        int solve(int[] A) {
            return recurse(A, -1, 0);
        }

        int recurse(int[] A, int previ, int ai) {
            if (ai == A.length)
                return A.length + 1;
            int a = A.length + 1;
            if ((previ == -1 || A[ai] != previ)) {
                a = recurse(A, A[ai + 1], ai + 2);
                if (a == A.length + 1)
                    a = 0;
                else
                    ++a;
            }
            if ((previ == -1 || previ == A[ai]) && ai + 1 < A.length) {
                int b = recurse(A, A[ai + 1], ai + 2);
                if (b != A.length + 1)
                    a = Math.min(a, b);
            }

            return a;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumDeletionRequiredToMakeCorrectForm().new Solution().solve(new int[] { 2,4,1,3,4,6,2,4,1,6 }));
    }
}
