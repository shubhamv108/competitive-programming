package code.shubham.topk;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    class Solution {
        public int connectSticks(int[] A) {
            int result = 0;

            PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> x - y);
            for (int a : A)
                q.offer(a);

            while (q.size() > 1) {
                int cost = q.poll() + q.poll();
                result += cost;
                q.offer(cost);
            }

            return result;
        }
    }

}
