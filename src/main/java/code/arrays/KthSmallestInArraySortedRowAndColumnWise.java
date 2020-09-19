package code.arrays;

import input.InputUtils;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class KthSmallestInArraySortedRowAndColumnWise {

    class Solution {

        int[][] A;
        int K;

        Solution(int[][] arr, int K) {
            this.A = arr;
            this.K = K;
        }

        int solve() {
            var maxHeap = new PriorityQueue<Integer>();
            var kInserted = false;
            int i = 0, j = 0;
            for (; i < A.length; i++) {
               for (; j < A.length; j++) {
                   maxHeap.offer(A[i][j]);
                   if (maxHeap.size() == K) {
                       kInserted = true;
                       break;
                   }
               }
               if (kInserted) break;
            }

            int endRow = A.length;
            int endColumn = A.length;
            for (; i < endRow; i++) {
                for (; j < endColumn; j++) {
                    if (A[i][j] < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(A[i][j]);
                    }
                }
            }
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int N = InputUtils.nextInt();
        var arr = new int[N][N];
        IntStream.range(0, N).forEach(i ->
            arr[i] = InputUtils.nextIntLine()
        );
        int K = InputUtils.nextInt();
        System.out.println(
                new KthSmallestInArraySortedRowAndColumnWise().new Solution(arr, K).solve()
        );
    }

}
