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
            var maxHeap = new PriorityQueue<Integer>((x, y) -> y -x);
            var kInserted = false;
            int i = 0, j = 0;
            for (; i < A.length; i++) {
               for (j = 0; j < A.length; j++) {
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
            j++;
            for (; i < endRow; i++) {
                for (; j < endColumn; j++) {
                    if (A[i][j] < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(A[i][j]);
                    } else {
                        endColumn = j;
                    }
                }
                j = 0;
                if (endColumn == 0) {
                    endRow = 0;
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
