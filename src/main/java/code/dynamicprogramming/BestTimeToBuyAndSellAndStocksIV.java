package code.dynamicprogramming;

public class BestTimeToBuyAndSellAndStocksIV {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int profit[][] = new int[k + 1][ n + 1];

        // For day 0, you can't earn money
        // irrespective of how many times you trade
        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        // profit is 0 if we don't do any
        // transation (i.e. k =0)
        for (int j = 0; j <= n; j++)
            profit[0][j] = 0;

        // fill the table in bottom-up fashion
        for (int i = 1; i <= k; i++) {
            int prevDiff = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                prevDiff = Math.max(prevDiff,
                        profit[i - 1][j - 1] - prices[j - 1]);
                profit[i][j] = Math.max(profit[i][j - 1], prices[j] + prevDiff);
            }
        }

        return profit[k][n - 1];
    }

    public static void main(String[] args) {
        int k = 3;
        int[] prices = {12, 14, 17, 10, 14, 13, 12, 15};

        System.out.println("Maximum profit is: " +
                maxProfit(k, prices));
    }

}
