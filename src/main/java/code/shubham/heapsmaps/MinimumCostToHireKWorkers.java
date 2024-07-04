package code.shubham.heapsmaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    class Solution {
        public double mincostToHireWorkers(int[] Q, int[] W, int K) {
            double[][] ws = new double[Q.length][2];
            for (int i = 0; i < Q.length; ++i)
                ws[i] = new double[] {
                        (double) (W[i]) / Q[i],
                        (double) Q[i]
                };
            Arrays.sort(ws, (x, y) -> (int) (x[0] - y[0]));

            double result = Double.MAX_VALUE, qsum = 0;

            PriorityQueue<Double> pq = new PriorityQueue<>();
            for (double[] w: ws) {
                qsum += w[1];
                pq.add(-w[1]);
                if (pq.size() > K)
                    qsum += pq.poll();
                if (pq.size() == K)
                    result = Math.min(result, qsum * w[0]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumCostToHireKWorkers()
                        .new Solution()
                        .mincostToHireWorkers(
                            new int[] {10,20,5},
                            new int[] {70,50,30},
                            2
                        )
        );
    }
}
