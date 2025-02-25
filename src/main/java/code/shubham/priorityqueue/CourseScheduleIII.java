package code.shubham.priorityqueue;

import java.util.Arrays;

public class CourseScheduleIII {
    class Solution {
        public int scheduleCourse(int[][] A) {
            Arrays.sort(A, (x, y) -> x[1] - y[1]);
            java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>((x, y) -> y - x);

            int t = 0;
            for (int[] a : A) {
                if (t + a[0] <= a[1]) {
                    pq.offer(a[0]);
                    t += a[0];
                } else if (!pq.isEmpty() && pq.peek() > a[0]) {
                    t -= pq.poll();
                    pq.offer(a[0]);
                    t += a[0];
                }
            }

            return pq.size();
        }
    }
}
