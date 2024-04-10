package code.shubham.dynamicprogramming;

import java.util.HashSet;

public class MinimumCostForTickets {
    class Solution {

        public int mincostTickets(int[] D, int[] C) {
            HashSet<Integer> days = new HashSet<>();
            for (int d : D)
                days.add(d);

            return solve(1, D, C, days, new Integer[D[D.length - 1] + 1]);
        }

        private int solve(int day, int[] D, int[] C, HashSet<Integer> days, Integer[] dp) {
            if (day > D[D.length - 1])
                return 0;

            if (!days.contains(day))
                return solve(day + 1, D, C, days, dp);

            if (dp[day] != null)
                return dp[day];


            int oneDay = C[0] + solve(day + 1, D, C, days, dp);
            int sevenDay = C[1] + solve(day + 7, D, C, days, dp);
            int thirtyDay = C[2] + solve(day + 30, D, C, days, dp);

            return dp[day] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
        }

    }
}
