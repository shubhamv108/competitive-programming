package code.shubham._01Knapsack;

public class PartitionEqualSubsetSum {

    class Solution {
        public boolean canPartition(int[] A) {
            int s = 0;
            for (int a : A)
                s += a;

            if ((s & 1) == 1)
                return false;

            s /= 2;

            boolean[] dp = new boolean[s + 1];
            dp[0] = true;
            for (int a : A)
                for (int i = s; i >= a; --i)
                    dp[i] = dp[i] || dp[i - a];

            return dp[s];
        }
    }

}
