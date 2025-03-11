package code.shubham.twoheaps;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    class MedianFinder {

        PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> min = new PriorityQueue<>();

        public MedianFinder() {

        }

        public void addNum(int n) {
            if (min.isEmpty() || n < min.peek())
                max.offer(n);
            else
                min.offer(n);

            balance();
        }

        public double findMedian() {
            if (max.size() == min.size())
                return (double) ((max.peek() + min.peek()) / 2d);
            return max.peek();
        }

        void balance() {
            if (max.size() - min.size() > 1)
                min.offer(max.poll());
            else if (max.size() < min.size())
                max.offer(min.poll());
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

}
