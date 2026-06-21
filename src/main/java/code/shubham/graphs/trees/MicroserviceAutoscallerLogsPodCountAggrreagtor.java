package code.shubham.graphs.trees;

import java.util.Arrays;

public class MicroserviceAutoscallerLogsPodCountAggrreagtor {

    public class Solution {

        public int[] finalCountOfMicroservicePods(int[] microservicesPodsCount, int[][] logs) {
            int n = microservicesPodsCount.length;
            int logsLength = logs.length;
            int curMax = 0;
            boolean[] visited = new boolean[n];

            for (int i = logsLength - 1; i >= 0; --i) {
                int operation = logs[i][0], microserviceOffset = logs[i][1] - 1, newPodCount = logs[i][2];
                if (operation == 1) {
                    if (!visited[microserviceOffset]) {
                        microservicesPodsCount[microserviceOffset] = Math.max(curMax, newPodCount);
                        visited[microserviceOffset] = true;
                    }
                }
                else
                    curMax = Math.max(curMax, newPodCount);
            }

            return microservicesPodsCount;
        }
    }

    void main(String[] args) {
        System.out.println(Arrays.toString(new MicroserviceAutoscallerLogsPodCountAggrreagtor().new Solution().finalCountOfMicroservicePods(
                new int[] { 1, 2, 1, 2, 1 },
                new int[][] {
                        { 1, 2, 1  },
                        { 2, -1, 1 }
                }
                                                                                                                                           )));
    }

}
