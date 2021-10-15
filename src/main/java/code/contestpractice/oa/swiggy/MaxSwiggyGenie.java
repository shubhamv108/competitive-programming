package code.contestpractice.oa.swiggy;

import java.util.Arrays;

public class MaxSwiggyGenie {
    class Solution {
        int solve(int[] pickup, int[] drop, int[] tip) {
            int len = pickup.length;
            int[][] schedule = new int[len][3];
            int[] dp = new int[len];
            for (int i = 0; i < len; i++) schedule[i] = new int[] { pickup[i], drop[i], tip[i] };
            Arrays.sort(schedule, (a, b) -> a[1] - b[1]);
            dp[0] = schedule[0][2];
            for (int i = 1; i < len; i++) {
                int curTip = schedule[i][2];
                int index = -1;
                for (int j = i-1; j > -1; j--) {
                    if (schedule[j][1] >= schedule[i][0]) {
                        index = j;
                        break;
                    }
                }
                if (index != -1)
                    curTip += dp[index];

                dp[i] = Math.max(dp[i], curTip);
            }
            return dp[len-1];
        }
    }

    public static void main(String[] args) {
        MaxSwiggyGenie maxSwiggyGenie = new MaxSwiggyGenie();
        System.out.println(
            maxSwiggyGenie.new Solution().solve(
                    new int[] {0,2,9,10,11,12},
                    new int[] {5,9,11,11,14,17},
                    new int[] {1,2,3,2,2,1})
        );
    }

}
