package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FindTheKSumOfAnArray {
    class Solution {
        public long kSum(int[] A, int k) {
            PriorityQueue<Long> minHeap = new PriorityQueue<>(k);
            int len = A.length;

            ArrayList<Long> sums = new ArrayList<>();
            sums.add(0L);
            minHeap.offer(0L);
            for (int i = 0; i < len; i++) {
                int size = sums.size();

                while (size-- > 0) {
                    long sum = A[i] + sums.get(size);
                    sums.add(sum);
                    if (minHeap.size() < k)
                        minHeap.offer(sum);
                    else if (sum > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.offer(sum);
                    }
                }

                // System.out.println(sums);
            }

            return minHeap.poll();
        }
    }
}
