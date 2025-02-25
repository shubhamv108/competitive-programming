package code.shubham.priorityqueue;

import java.util.Scanner;

public class KtLargestElementInSTREAM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int k = sc.nextInt();
            int n = sc.nextInt();
            int[] arr = new int[k];
            PriorityQueue minHeapOfSizeK = new PriorityQueue(arr, 0, true);
            while(n-- > 0) {
                int e = sc.nextInt();
                if (minHeapOfSizeK.size() >= k-1) {
                    minHeapOfSizeK.addForcefully(e);
                    System.out.printf("%d ", minHeapOfSizeK.peek());
                } else {
                    minHeapOfSizeK.add(e);
                    System.out.printf("%d ", -1);
                }
            }
        }
    }
}
