package code.shubham.dynamicprogramming;

public class MinimumIncreaseToMaximizeSpecialIndices {
    class Solution {
        public long minIncrease(int[] A) {
            return recurse(A, A.length, 1);
        }

        int recurse(int[] A, int n, int ai) {
            if (ai >= n-1)
                return 0;

            int operations = Math.max(0, Math.max(A[ai - 1], A[ai + 1]) + 1 - A[ai]);
            int t = A[ai];
            A[ai] += operations;
            int take = operations + recurse(A, n, ai + 2);
            A[ai] = t;
            int skip = recurse(A, n, ai + 1);

            return Math.min(take, skip);
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumDeletionRequiredToMakeCorrectForm().new Solution().solve(new int[] { 1, 2, 2 }));
        System.out.println(new MinimumDeletionRequiredToMakeCorrectForm().new Solution().solve(new int[] { 2, 1, 1, 3 }));
    }
}
