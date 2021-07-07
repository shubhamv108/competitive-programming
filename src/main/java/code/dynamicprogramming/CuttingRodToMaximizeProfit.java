package code.dynamicprogramming;

import java.util.ArrayList;

public class CuttingRodToMaximizeProfit {

    int getMaxProfit(int rodLength, int[] a) {
        int[] dp = new int[rodLength+1];
        int i, j = 0;

        for (i = 1; i < a.length; i++) {
            for (j = i; j < rodLength+1; j++) {
               dp[j] = Math.max(dp[j], a[i-1] + dp[j-i]);
            }
        }
       return dp[j-1];
    }

    ArrayList<Integer> getLocations(int rodLength, int[] prices) {
        ArrayList<Integer> result = new ArrayList<>();
        int[][] dp = new int[prices.length+1][rodLength+1];
        int i = 0, j = 0;
        for (i = 1; i < prices.length + 1; i++) {
            for (j = 1; j < rodLength + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], j >= i ? prices[i - 1] + dp[i][j - i] : 0);
            }
        }

        i--;j--;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j]) {
                i -= 1;
            } else {
                result.add(i);
                j -= i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        new CuttingRodToMaximizeProfit().getMaxProfit(5, new int[] { 2, 5, 7, 8 });
        new CuttingRodToMaximizeProfit().getLocations(5, new int[] { 2, 5, 7, 8 })
            .forEach(System.out::println);
    }

}
