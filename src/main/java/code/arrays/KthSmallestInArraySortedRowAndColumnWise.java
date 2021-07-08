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

    class Solution2 {
        class Pair {
            int r;
            int c;
            Pair(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        public int kthSmallest(int[][] matrix, int k) {
            int result = matrix[0][0];
            PriorityQueue<Pair> minHeap = new PriorityQueue<>((n, m) -> matrix[n.r][n.c] - matrix[m.r][m.c]);

            for (int i = 0; i < Math.min(k, matrix.length); i++) {
                minHeap.offer(new Pair(i, 0));
            }

            int count = 0;
            while (!minHeap.isEmpty()) {
                Pair p = minHeap.poll();
                if (++count == k) {
                    result = matrix[p.r][p.c];
                    break;
                }
                p.c++;
                if (matrix[0].length > p.c) {
                    minHeap.offer(p);
                }
            }
            return result;
        }
    }

    class Solution3 {
        public int kthSmallest(int[][] matrix, int k) {
            int l = matrix[0][0], r = matrix[matrix.length-1][matrix[0].length-1];
            while (l <= r) {
                int m = l + (r - l) / 2;
                int count = getCount(matrix, m);
                if (count < k) l = m + 1;
                else r = m - 1;
            }
            return l;
        }

        int getCount(int[][] matrix, int val) {
            int count = 0;
            int i = matrix.length - 1, j = 0;
            while (i > -1 && j < matrix.length) {
                if (matrix[i][j] > val) {
                    i--;
                } else {
                    count += i + 1;
                    j++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new KthSmallestInArraySortedRowAndColumnWise().new Solution3().kthSmallest(
                        new int[][] {
                                {1, 5, 9},
                                {10, 11, 13},
                                {12, 13, 15}
                        },
                        8
                )
        );
    }

}
