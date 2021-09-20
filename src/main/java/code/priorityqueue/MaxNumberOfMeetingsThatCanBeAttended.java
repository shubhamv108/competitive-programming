package code.priorityqueue;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class MaxNumberOfMeetingsThatCanBeAttended {
    class Solution {
        public int maxEvents(int[][] A) {
            Arrays.sort(A, (a, b) -> a[0] - b[0]);
            PriorityQueue<Integer> q = new PriorityQueue<Integer>();
            int i = 0, result = 0, n = A.length;
            for (int d = 1; d <= 100000; d++) {
                while (!q.isEmpty() && q.peek() < d) q.poll();
                while (i < n && A[i][0] == d) q.offer(A[i++][1]);
                if (!q.isEmpty()) {
                    q.poll();
                    result++;
                }
            }
            return result;
        }
    }
}