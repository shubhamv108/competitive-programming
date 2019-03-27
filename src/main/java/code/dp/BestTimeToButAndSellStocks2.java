package code.dp;

import java.util.List;

public class BestTimeToButAndSellStocks2 {
    class Solution {
        public int maxProfit(final List<Integer> A) {
            int min = 0;
            int maxProfit = 0;
            for ( int i = 1; i < A.size(); i++ ) {
                if (A.get(i) < A.get(min)) min = i;
                else {
                    maxProfit += A.get(i) - A.get(min);
                    min = i;
                }
            }
            return maxProfit;
        }
    }

    class Solution2 {
        public int maxProfit(int[] prices) {
            int profit = 0;
            for ( int i = 1; i < prices.length; i++ ) {
                if (prices[i] < prices[0]) prices[0] = prices[i];
                else {
                    profit += prices[i] - prices[0];
                    prices[0] = prices[i];
                }
            }
            return profit;
        }
    }
}
