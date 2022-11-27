package code.shubham.backtracking;

import java.util.Arrays;
import java.util.BitSet;

public class CampusBikesII {

    // Recursive
    class Solution {
        int minDist = Integer.MAX_VALUE;

        public int assignBikes(int[][] workers, int[][] bikes) {
            recurse(workers, 0, bikes, new BitSet(), 0);
            return this.minDist;
        }

        void recurse(int[][] workers, int workerIdx, int[][] bikes, BitSet assignedBikes, int curDist) {
            if (workerIdx == workers.length) {
                this.minDist = Math.min(curDist, this.minDist);
                return;
            }
            for (int i = 0; i < bikes.length; i++) {
                if (!assignedBikes.get(i)) {
                    assignedBikes.set(i);
                    recurse(workers, workerIdx + 1, bikes, assignedBikes,
                            curDist + dist(workers[workerIdx], bikes[i]));
                    assignedBikes.set(i, false);
                }
            }
        }

        int dist(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }

    // Recursive - DP
    class Solution2 {
        public int assignBikes(int[][] workers, int[][] bikes) {
            int[] dp = new int[1 << bikes.length];
            Arrays.fill(dp,-1);
            return recurse(workers, 0, bikes, 0, 0, dp);
        }

        int recurse(int[][] workers, int workerIdx, int[][] bikes, int state, int curDist, int[] dp) {
            if (workerIdx == workers.length)
                return dp[state] = curDist;
            if (dp[state] != -1) return dp[state];
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < bikes.length; i++) {
                if ((state & (1 << i)) == 0) {
                    int dist = recurse(workers, workerIdx + 1, bikes,
                            state | (1 << i),
                            curDist + dist(workers[workerIdx], bikes[i]), dp);
                    minDist = Math.min(dist, minDist);
                }
            }
            return dp[state] = minDist;
        }

        int dist(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CampusBikesII().new Solution2().assignBikes(
                        new int[][] { {0, 0}, {2, 1} },
                        new int[][] { {1, 2}, {3, 3} })
        );
    }
}
