package code.shubham.priorityqueue;

import java.util.Queue;
import java.util.Scanner;

public class  RunningMedianOfIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        System.out.print(0 - 'a');
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] minHeapArr = new int[n];
            minHeapArr[0] = Integer.MAX_VALUE;
            PriorityQueue minHeap = new PriorityQueue(minHeapArr, 0, true);

            int[] maxHeapArr = new int[n];
            maxHeapArr[0] = Integer.MIN_VALUE;
            PriorityQueue maxHeap = new PriorityQueue(maxHeapArr, 0, false);

            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                if (maxHeap.peek() == Integer.MIN_VALUE && minHeap.peek() == Integer.MAX_VALUE) maxHeap.add(a);
                else {
                    if (a <= maxHeap.peek()) maxHeap.add(a);
                    else minHeap.add(a);
                }
                if (minHeap.size() == maxHeap.size() - 2) minHeap.add(maxHeap.poll());
                else if (maxHeap.size() == minHeap.size() - 2) maxHeap.add(minHeap.poll());
                if ((i & 1) == 1) System.out.printf("%.2f ", ((double) (minHeap.peek() + maxHeap.peek())) / 2);
                else if (minHeap.size() > maxHeap.size()) System.out.printf("%.2f ", (double) minHeap.peek());
                else System.out.printf("%.2f ", (double) maxHeap.peek());
            }
            System.out.printf("\n");
        }
    }

}
class JavaQueue {
    //using JDK Priority Queue
    public static void main () {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        System.out.print(0 - 'a');
        while(t-- > 0) {
            int n = sc.nextInt();

            Queue<Integer> minHeap = new java.util.PriorityQueue<>();
            minHeap.add(Integer.MAX_VALUE);

            Queue<Integer> maxHeap = new java.util.PriorityQueue<>((x, y) -> y - x);
            maxHeap.add(Integer.MIN_VALUE);


            for(int i = 0; i < n; i++) {
                int a = sc.nextInt();
                if(maxHeap.peek() == Integer.MIN_VALUE && minHeap.peek() == Integer.MAX_VALUE) maxHeap.add(a);
                else {
                    if (a <= maxHeap.peek()) maxHeap.add(a);
                    else minHeap.add(a);
                }
                if (minHeap.size() == maxHeap.size() - 2) minHeap.add(maxHeap.poll());
                else if (maxHeap.size() == minHeap.size() - 2) maxHeap.add(minHeap.poll());
                if (((i & 1) == 1) || maxHeap.size() == minHeap.size()) System.out.printf("%.2f ", ((double) (minHeap.peek() + maxHeap.peek()))/2);
                else if (minHeap.size() > maxHeap.size()) System.out.printf("%.2f ", (double) minHeap.peek());
                else System.out.printf("%.2f ", (double) maxHeap.peek());
            }
            System.out.printf("\n");
        }
    }
}

class MedianFinder {

    /** initialize your data structure here. */

    Queue<Integer> maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
    Queue<Integer> minHeap = new java.util.PriorityQueue<>((a, b) -> a - b);

    public MedianFinder() {
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() && minHeap.isEmpty())
            maxHeap.offer(num);
        else if (num <= maxHeap.peek())
            maxHeap.offer(num);
        else
            minHeap.offer(num);
        this.balanceHeaps();
    }

    private void balanceHeaps() {
        if (minHeap.size() == maxHeap.size() - 2)
            minHeap.offer(maxHeap.poll());
        else if (maxHeap.size() == minHeap.size() - 2)
            maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        double result = maxHeap.peek();
        if (minHeap.size() == maxHeap.size()) result = ((double) maxHeap.peek() + minHeap.peek()) / 2;
        else if (minHeap.size() > maxHeap.size()) result = minHeap.peek();
        return result;
    }
}

class MedianFinder2 {
    java.util.PriorityQueue<Integer> maxHeap, minHeap;

    /** initialize your data structure here. */
    public MedianFinder2() {
        this.minHeap = new java.util.PriorityQueue<>((a, b) -> a - b); //  5, 8, 9
        this.maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a); // 4, 1
    }

    public void addNum(int n) {
        if (maxHeap.size() == minHeap.size()) {
            if (minHeap.isEmpty()) {
                maxHeap.offer(n);
            }
            else if (n > maxHeap.peek()) {
                minHeap.offer(n);
            } else {
                maxHeap.offer(n);
            }
        } else if (maxHeap.size() > minHeap.size()) {
            if (n >= maxHeap.peek()) minHeap.offer(n);
            else {
                maxHeap.offer(n);
                minHeap.offer(maxHeap.poll());
            }
        } else {
            if (n <= minHeap.peek()) maxHeap.offer(n);
            else {
                minHeap.offer(n);
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) return -1.0d;
        if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
        else if (maxHeap.size() < minHeap.size()) return minHeap.peek();
        else return ((maxHeap.peek() + minHeap.peek()) * 1.0) / 2;
    }
}

