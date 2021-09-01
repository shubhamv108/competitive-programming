package code.dynamicprogramming;

import java.util.Arrays;
import java.util.TreeMap;

public class MaxProfitInJobScheduling {

    class Solution {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            int[][] jobs = new int[n][3];
            for (int i = 0; i < n; i++)
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
            TreeMap<Integer, Integer> dp = new TreeMap<>();
            dp.put(0, 0);
            for (int[] job : jobs) {
                int cur = dp.floorEntry(job[0]).getValue() + job[2];
                if (cur > dp.lastEntry().getValue())
                    dp.put(job[1], cur);
            }
            return dp.lastEntry().getValue();
        }
    }

    class Solution2 {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            int[][] job = new int[n][3];
            for (int i = 0; i < n; i++)
                job[i] = new int[] { startTime[i], endTime[i], profit[i] };
            Arrays.sort(job, (a, b) -> a[1] - b[1]);
            int[] dp = new int[n];

            for (int i = 0; i < n; i++) {
                int p = job[i][2];
                for (int j = 0; j < i; j++) {
                    if (job[j][1] <= job[i][0] && dp[j] + p >= dp[i]) {
                        dp[i] = dp[j] + p;
                    }
                }
            }
            return Arrays.stream(dp).max().getAsInt();
        }
    }

    class Solution3 {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++)
                jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
            Arrays.sort(jobs, (a, b) -> Integer.compare(a.end, b.end));
            int[] dp = new int[n];
            dp[0] = jobs[0].profit;
            for (int i = 1;i < n; i++){
                int currentProfit = jobs[i].profit;
                int index = -1;
                for (int j = i - 1; j >= 0; j--) {
                    if(jobs[j].end <= jobs[i].start){
                        index = j;
                        break;
                    }
                }
                if (index != -1){
                    currentProfit += dp[index];
                }
                dp[i] = Math.max(currentProfit, dp[i-1]);
            }
            return dp[n-1];
        }

        class Job {
            int start;
            int end;
            int profit;

            public Job(int start, int end, int profit) {
                this.start=start;
                this.end=end;
                this.profit=profit;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxProfitInJobScheduling().new Solution3().jobScheduling(
                        new int[] { 1, 2, 3, 4, 6 },
                        new int[] { 3, 5, 10, 6, 9 },
                        new int[] { 20, 20, 100, 70, 60 }
                )
        );
    }

}
