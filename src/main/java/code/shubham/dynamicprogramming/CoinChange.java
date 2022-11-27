package code.shubham.dynamicprogramming;

public class CoinChange {
    class Solution {
        public int coinChange(int[] coins, int amount) {
//            Arrays.sort(coins);
            Integer[] dp = new Integer[amount+1];
            dp[0] = 0;
            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    if (dp[i - coin] != null)
                        if (dp[i] != null)
                            dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                        else
                            dp[i] = 1 + dp[i - coin];
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
