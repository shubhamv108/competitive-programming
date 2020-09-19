package code.dynamicprogramming;

public class MinStepsToReachFromStartToEnd {

    class SolutionRecursive {
        int recurse(int[] A, int l, int r) {
            if (l == r) return 0;
            int minSteps = Integer.MAX_VALUE;
            if (A[l] == 0) return minSteps;
            for (int i = l + 1; i <= l + A[l] && i <= r; i++) {
                int steps = recurse(A, i, r);
                if (steps != Integer.MAX_VALUE) {
                    minSteps = Math.min(minSteps, steps + 1);
                }
            }
            return minSteps;
        }

        int solve(int[] A) {
            return recurse(A, 0, A.length - 1);
        }
    }

    class SolutionDP {

        SolutionDP(int[] A) {

        }

        int recurse(int[] A, int l, int r) {
            if (l == r) return 0;
            int minSteps = Integer.MAX_VALUE;
            if (A[l] == 0) return minSteps;
            for (int i = l + 1; i <= r && i <= l + A[l]; i++) {
                int steps = recurse(A, i, r);
                if (steps != Integer.MAX_VALUE) {
                    minSteps = Math.min(minSteps, steps + 1);
                }
            }
            return minSteps;
        }

        int solve(int[] A) {
            return recurse(A, 0, A.length - 1);
        }
    }

}
