package code.shubham.dynamicprogramming.jumpgame;

public class JumpGameI {
    class Solution3 {
        public boolean canJump(int[] A) {
            int cur = 0;
            for (int i = 0; i <= cur; i++) {
                cur = Math.max(cur, i + A[i]);
                if (cur >= A.length - 1)
                    return true;
            }
            return false;
        }
    }

    class Solution2 {
        public boolean canJump(int[] A) {
            int next = A.length - 1;
            for (int i = A.length - 2; i > -1; i--)
                if (i + A[i] >= next)
                    next = i;
            return next == 0;
        }
    }

    class Solution1 {
        public boolean canJump(int[] A) {
            boolean[] dp = new boolean[A.length];
            dp[A.length - 1] = true;
            for (int i = A.length - 2; i > -1; i--) {
                for (int j = i; j <= i+A[i]; j++) {
                    if (dp[j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[0];
        }
    }
}
