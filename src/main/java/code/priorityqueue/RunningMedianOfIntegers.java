package code.priorityqueue;

import java.util.Queue;
import java.util.Scanner;

public class RunningMedianOfIntegers {
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
                if ((i & 1) == 1) System.out.printf("%.2f ", ((double) (minHeap.peek() + maxHeap.peek()))/2);
                else if (minHeap.size() > maxHeap.size()) System.out.printf("%.2f ", (double) minHeap.peek());
                else System.out.printf("%.2f ", (double) maxHeap.peek());
            }
            System.out.printf("\n");
        }
    }
}
