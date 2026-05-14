package code.shubham.bitmanipulation.arrays;

public class FindAllPossibleStableBinaryArraysI {
    class Solution {
        int MOD = 1000000007;

        public int numberOfStableArrays(int zero, int one, int limit) {
            long result = 0;
            Integer[][][] dp = new Integer[201][201][2];

            for (int i = 1; i <= limit && i <= zero; ++i)
                result = (result + solve(i, 0, 0, zero, one, limit, dp)) % MOD;

            for (int i = 1; i <= limit && i <= one; ++i)
                result = (result + solve(0, i, 1, zero, one, limit, dp)) % MOD;

            return (int) result;
        }

        int solve(int z, int o, int last, int zero, int one, int limit, Integer[][][] dp) {
            if (z == zero && o == one)
                return 1;

            if (dp[z][o][last] != null)
                return dp[z][o][last];

            long c = 0;

            if (last == 0)
                for (int k = 1; k <= limit && o + k <= one; k++)
                    c = (c + solve(z, o + k, 1, zero, one, limit, dp)) % MOD;
            else
                for (int k = 1; k <= limit && z + k <= zero; k++)
                    c = (c + solve(z + k, o, 0, zero, one, limit, dp)) % MOD;

            return dp[z][o][last] = (int) c;
        }
    }
}
