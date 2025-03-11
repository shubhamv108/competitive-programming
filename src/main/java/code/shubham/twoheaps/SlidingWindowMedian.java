package code.shubham.twoheaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    class Solution {
        public double[] medianSlidingWindow(int[] A, int k) {
            double[] result = new double[A.length - k + 1];
            int ri = 0;

            var finder = new MedianFinder();
            int i = 0;
            for (; i < k; ++i) {
                finder.addNum(A[i]);
                finder.balance();
            }

            result[ri++] = finder.findMedian();

            for (; i < A.length; ++i) {
                finder.addNum(A[i]);
                finder.remove(A[i - k]);
                finder.balance();
                result[ri++] = finder.findMedian();
            }

            return result;
        }
    }

    class MedianFinder {

        PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> y.compareTo(x));
        PriorityQueue<Integer> min = new PriorityQueue<>((x, y) -> x.compareTo(y));

        public void addNum(int n) {
            if (min.isEmpty() || n < min.peek())
                max.offer(n);
            else
                min.offer(n);
        }

        void balance() {
            if (max.size() - min.size() > 1)
                min.offer(max.poll());
            else if (max.size() < min.size())
                max.offer(min.poll());
        }

        public double findMedian() {
            if (max.size() == min.size())
                return ((double) max.peek() + min.peek()) / 2;
            return max.peek();
        }

        public void remove(int n) {
            if (min.isEmpty() || n < min.peek())
                max.remove(n);
            else
                min.remove(n);
        }
    }

    public static void main(String[] args) {
        var s = new SlidingWindowMedian().new Solution().medianSlidingWindow(
                new int[] { -2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648 }, 3);
        Arrays.stream(s).forEach(System.out::println);
    }

}
