package code.shubham.contestpractice.oa.swiggy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaxSwiggyGenie {
    class Solution {
        long solve(int[] pickup, int[] drop, int[] tip) {
            int len = pickup.length;
            int[][] schedule = new int[len][3];
            for (int i = 0; i < len; i++) schedule[i] = new int[] { pickup[i], drop[i], tip[i] };
            Arrays.sort(schedule, (a, b) -> a[1] - b[1]);
            long[] dp = new long[len];
            dp[0] = schedule[0][1] -  schedule[0][0] +  schedule[0][2];
            for (int i = 1; i < len; i++) {
                long curTip = schedule[i][1] -  schedule[i][0] +  schedule[i][2];
                int index = -1;
                for (int j = i-1; j > -1; j--) {
                    if (schedule[j][1] <= schedule[i][0]) {
                        index = j;
                        break;
                    }
                }
                if (index != -1)
                    curTip += dp[index];

                dp[i] = Math.max(dp[i-1], curTip);
            }
            return dp[len-1];
        }
    }

    class Solution2 {
        long solve(int[] pickup, int[] drop, int[] tip) {
            int len = pickup.length;
            int[][] schedule = new int[len][3];
            for (int i = 0; i < len; i++) schedule[i] = new int[] { pickup[i], drop[i], tip[i] };
            Arrays.sort(schedule, (a, b) -> a[1] - b[1]);
            
            TreeMap<Integer, Long> cache = new TreeMap<>();
            cache.put(schedule[0][1], (long) schedule[0][1] -  schedule[0][0] +  schedule[0][2]);
            long prev = schedule[0][1] -  schedule[0][0] +  schedule[0][2];

            for (int i = 1; i < schedule.length; i++) {
                long curEarn = schedule[i][1] -  schedule[i][0] +  schedule[i][2];
                Map.Entry<Integer, Long> lower = cache.floorEntry(schedule[i][0]);
                if (lower != null)
                    curEarn += lower.getValue();
                curEarn = Math.max(curEarn, prev);
                cache.put(schedule[i][1], curEarn);
                prev = curEarn;
            }
            return prev;
        }
    }

    public static void main(String[] args) {
        MaxSwiggyGenie maxSwiggyGenie = new MaxSwiggyGenie();
        System.out.println(
            maxSwiggyGenie.new Solution2().solve(
                    new int[] {0,2,9,10,11,12},
                    new int[] {5,9,11,11,14,17},
                    new int[] {1,2,3,2,2,1})
        );
    }

}
