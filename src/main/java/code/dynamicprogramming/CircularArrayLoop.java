package code.dynamicprogramming;

import java.util.HashSet;

public class CircularArrayLoop {
    class Solution {
        public boolean circularArrayLoop(int[] A) {
            int len = A.length, sign;
            Boolean[][] dp = new Boolean[2][len];
            HashSet<Integer> visited = new HashSet<>();
            for (int i = 0; i < len; i++) {
                sign = sign(A[i]);
                if (dp[sign][i] = visit(A, i, sign, dp, visited))
                    return true;
                visited.clear();
            }
            return false;
        }

        boolean visit(int[] A, int idx, int sign, Boolean[][] dp, HashSet<Integer> visited) {
            if (idx >= A.length)
                return false;

            if (A[idx] == 0)
                return false;

            if (sign(A[idx]) != sign)
                return false;

            if (visited.contains(idx))
                return visited.size() > 1;

            if (dp[sign][idx] != null)
                return dp[sign][idx];

            int nextIdx;
            if (sign == 0)
                nextIdx = (idx + A[idx]) % A.length;
            else
                nextIdx = (A.length + ((idx + A[idx]) % A.length)) % A.length;


            if (idx == nextIdx)
                return dp[sign][idx] = false;

            visited.add(idx);
            return dp[sign][idx] = visit(A, nextIdx, sign, dp, visited);
        }

        int sign(int n) {
            return n > 0 ? 0 : 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CircularArrayLoop().new Solution().
                        circularArrayLoop(
                                new int[] { -2, -3, -9})
        );
    }
}
