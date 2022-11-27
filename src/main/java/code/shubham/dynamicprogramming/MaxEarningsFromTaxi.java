package code.shubham.dynamicprogramming;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaxEarningsFromTaxi {
    class Solution {
        public long maxTaxiEarnings(int n, int[][] rides) {
            Arrays.sort(rides, (a, b) -> a[1] - b[1]);

            long[] dp = new long[rides.length];
            dp[0] = rides[0][1] -  rides[0][0] +  rides[0][2];

            for (int i = 1; i < rides.length; i++) {
                long curEarn = rides[i][1] -  rides[i][0] +  rides[i][2];
                int index = -1;
                for (int j = i-1; j > -1; j--) {
                    if (rides[j][1] <= rides[i][0]) {
                        index = j;
                        break;
                    }
                }
                if (index != -1)
                    curEarn += dp[index];
                dp[i] = Math.max(dp[i-1], curEarn);
            }
            return dp[rides.length-1];
        }
    }

    class Solution2 {
        public long maxTaxiEarnings(int n, int[][] rides) {
            Arrays.sort(rides, (a, b) -> a[1] - b[1]);

            TreeMap<Integer, Long> cache = new TreeMap<>();
            cache.put(rides[0][1], (long) rides[0][1] -  rides[0][0] +  rides[0][2]);
            long prev = rides[0][1] -  rides[0][0] +  rides[0][2];

            for (int i = 1; i < rides.length; i++) {
                long curEarn = rides[i][1] -  rides[i][0] +  rides[i][2];
                Map.Entry<Integer, Long> lower = cache.floorEntry(rides[i][0]);
                if (lower != null)
                    curEarn += lower.getValue();
                curEarn = Math.max(curEarn, prev);
                cache.put(rides[i][1], curEarn);
                prev = curEarn;
            }
            return prev;
        }
    }

    public static void main(String[] args) {
        MaxEarningsFromTaxi maxEarningsFromTaxi = new MaxEarningsFromTaxi();
        Solution solution = maxEarningsFromTaxi.new Solution();
        System.out.println(solution.maxTaxiEarnings(5, new int[][] {
                {2, 5, 4},
                {1, 5, 1}
        }));
    }
}
