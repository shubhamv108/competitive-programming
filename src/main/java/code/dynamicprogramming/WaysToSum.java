package code.dynamicprogramming;

import java.util.ArrayList;

public class WaysToSum {

    class Solution {

        int N;
        int K;
        Integer[][] dp;
        Solution(int N, int k) {
            this.N = N;
            this.K = k;
            this.dp = new Integer[N+1][k+1];
        }

        int solve() {
            return recurse(1, K, new ArrayList<Integer>());
        }

        int recurse(int start, int sum, ArrayList<Integer> l) {
            if (sum < 0) return 0;
            if (sum == 0) { System.out.println(l); return 1; }
            if (dp[start][sum] != null) return dp[start][sum];
            int result = 0;
            for (int i = start; i <= N; i++) {
                l.add(i);
                result += recurse(i, sum - i, l);
                l.remove(l.size() - 1);
            }
            return dp[start][sum] = result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
        new WaysToSum().new Solution(2, 8).solve());
    }

}
