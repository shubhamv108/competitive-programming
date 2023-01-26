package code.shubham.dynamicprogramming;

public class CoinChange2 {
    class Solution {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int coin : coins)
                for (int amt = coin; amt <= amount; amt++)
                    dp[amt] += dp[amt-coin];
            return dp[amount];
        }
    }
}
