package code.shubham.graphs.floydwarshall;

public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
    class Solution {
        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            Integer[][] distance = new Integer[n][n];
            for (int[] e: edges)
                distance[e[0]][e[1]] = distance[e[1]][e[0]] = e[2];

            for (int  i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        distance[i][i] = 0;
                    else if (distance[i][j] == null)
                        distance[i][j] = 10001;
                }
            }

            for (int k = 0; k < n; k++)
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        distance[i][j] = Math.min(
                                distance[i][j],
                                distance[i][k] + distance[k][j]);

            int min = n, result = 0;
            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = 0; j < n; j++)
                    if (distance[i][j] <= distanceThreshold)
                        count++;

                if (count <= min) {
                    min = count;
                    result = i;
                }
            }
            return result;
        }
    }
}
