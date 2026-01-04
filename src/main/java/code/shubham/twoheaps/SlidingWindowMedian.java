package code.shubham.twoheaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

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

    class Solution2 {

        int[] A;
        TreeSet<Integer> max, min;

        public double[] medianSlidingWindow(int[] A, int k) {
            this.A = A;
            double[] result = new double[A.length - k + 1];
            int ri = 0;

            max = new TreeSet<>((x, y) -> A[x] == A[y] ? x - y : Integer.compare(A[y], A[x]));
            min = new TreeSet<>((x, y) -> A[x] == A[y] ? x - y : Integer.compare(A[x], A[y]));

            int i = 0;
            for (; i < k; ++i) {
                add(i);
                balance();
            }

            result[ri++] = median();

            for (; i < A.length; ++i) {
                add(i);
                remove(i-k);
                balance();
                result[ri++] = median();
            }

            return result;
        }

        void add(int i) {
            if (max.isEmpty() || A[i] <= A[max.first()])
                max.add(i);
            else
                min.add(i);
        }

        void remove(int i) {
            if (!min.remove(i))
                max.remove(i);
        }

        void balance() {
            if (max.size() - min.size() > 1)
                min.add(max.removeFirst());
            else if (min.size() > max.size())
                max.add(min.removeFirst());
        }

        double median() {
            if (max.size() == min.size())
                return ((double) A[max.first()] + (double) A[min.first()]) / 2d;
            return A[max.first()];
        }
    }


    public static void main(String[] args) {
        var s = new SlidingWindowMedian().new Solution2().medianSlidingWindow(
                new int[] { -2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648 }, 3);
        Arrays.stream(s).forEach(System.out::println);
    }

}
