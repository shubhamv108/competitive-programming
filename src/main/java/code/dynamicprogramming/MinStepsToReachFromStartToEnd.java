package code.dynamicprogramming;

import input.InputUtils;

public class MinStepsToReachFromStartToEnd {

    class SolutionDP {

        int[] A;
        int[] dp;
        SolutionDP(int[] A) {
            this.A = A;
            this.dp = new int[A.length];
        }

        int solve() {
            if (A.length == 0 || A[0] == 0)
                return Integer.MAX_VALUE;
            dp[A.length - 1] = 0;
            for (int i = A.length - 2; i > -1 ; i--) {
                if (A[i] == 0) dp[i] = Integer.MAX_VALUE;
                if (i + A[i] >= A.length - 1) dp[i] = 1;
                else {
                    dp[i] = Integer.MAX_VALUE;
                    for (int j = i + 1; j < A.length && j <= i + A[i]; j++) {
                        if (dp[j] != Integer.MAX_VALUE) {
                            dp[i] = Math.min(dp[i], dp[j] + 1);
                        }
                    }
                }
            }
            return dp[0];
        }
    }

    public static void main(String[] args) {
        int[] A = InputUtils.nextIntLine();
        System.out.println(
                new MinStepsToReachFromStartToEnd().new SolutionDP(A).solve()
        );
    }

}
