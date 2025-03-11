package code.shubham.topk;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {

    class KthLargest {

        int k;
        PriorityQueue<Integer> q = new PriorityQueue<>();

        public KthLargest(int k, int[] A) {
            this.k = k;
            for (int a : A)
                add(a);
        }

        public int add(int val) {
            if (q.size() < k) {
                q.offer(val);
            } else if (q.peek() < val) {
                q.poll();
                q.offer(val);
            }

            return q.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

}
