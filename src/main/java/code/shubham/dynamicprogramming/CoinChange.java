package code.shubham.dynamicprogramming;

public class CoinChange {
    class Solution {
        public int coinChange(int[] coins, int amount) {
            Integer[] dp = new Integer[amount+1];
            dp[0] = 0;
            for (int coin : coins) {
                for (int amt = coin; amt <= amount; amt++) {
                    if (dp[amt - coin] != null)
                        if (dp[amt] == null)
                            dp[amt] = 1 + dp[amt - coin];
                        else
                            dp[amt] = Math.min(dp[amt], 1 + dp[amt - coin]);
                }
            }
            return dp[amount] == null ? -1 : dp[amount];
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CoinChange().new Solution().coinChange(new int[]{186, 419, 83, 408}, 6249)
        );
    }
}
