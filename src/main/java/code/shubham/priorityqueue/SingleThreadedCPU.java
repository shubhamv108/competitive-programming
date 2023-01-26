package code.shubham.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {

    class Solution {
        public int[] getOrder(int[][] tasks) {
            int result[] = new int[tasks.length], r = 0;
            int A[][] = new int[tasks.length][3];
            for (int i = 0; i < tasks.length; i++)
                A[i] = new int[] { tasks[i][0], tasks[i][1], i };

            Arrays.sort(A, (x, y) -> x[0] - y[0]);

            PriorityQueue<int[]> q = new PriorityQueue<>(
                    (x, y) -> x[1] == y[1] ? x[2] - y[2] : x[1] - y[1]);


            int nextIndex = 0, maxTime = 0;
            while (r < tasks.length) {
                while (nextIndex < A.length && A[nextIndex][0] <= maxTime)
                    q.offer(A[nextIndex++]);

                if (q.isEmpty() & nextIndex < tasks.length) {
                    maxTime = A[nextIndex][0];
                    q.offer(A[nextIndex++]);
                    continue;
                }

                int[] p = q.poll();
                result[r++] = p[2];
                maxTime += p[1];
            }


            return result;
        }
    }

    public static void main(String[] args) {
        SingleThreadedCPU singleThreadedCPU = new SingleThreadedCPU();
        Solution solution = singleThreadedCPU.new Solution();
        Arrays.stream(solution.getOrder(new int[][] {{1,2},{2,4},{3,2},{4,1}}))
                .forEach(e -> System.out.println(e + "->"));
    }
}
