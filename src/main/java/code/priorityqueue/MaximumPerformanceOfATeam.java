package code.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumPerformanceOfATeam {
    class Solution {
        public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            int[][] A = new int[n][2];
            for (int i = 0; i < n; i++)
                A[i] = new int[] { speed[i], efficiency[i] };
            Arrays.sort(A, (x, y) -> y[1] - x[1]);

            long result = 0, speedSum = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int[] a : A) {
                pq.offer(a[0]);
                speedSum += a[0];
                if (pq.size() > k)
                    speedSum -= pq.poll();

                result = Math.max(speedSum, result * a[1]);
            }
            return (int) (result % (long) (1e9 + 7));
        }
    }

    public static void main(String[] args) {
        int n = 6, speed[] = {2,10,3,1,5,8}, efficiency[] = {5,4,3,9,7,2}, k = 2;
        System.out.println(
                new MaximumPerformanceOfATeam().new Solution().maxPerformance(n, speed, efficiency, k)
        );
    }
}
