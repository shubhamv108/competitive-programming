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
            PriorityQueue minHeapofSizeK = new PriorityQueue(arr, 0, true);
            while(n-- > 0) {
                int e = sc.nextInt();
                if (minHeapofSizeK.size() >= k-1) {
                    minHeapofSizeK.addForcefully(e);
                    System.out.printf("%d ", minHeapofSizeK.peek());
                } else {
                    minHeapofSizeK.add(e);
                    System.out.printf("%d ", -1);
                }
            }
        }
    }
}
