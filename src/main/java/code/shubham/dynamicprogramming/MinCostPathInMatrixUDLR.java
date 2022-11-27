package code.shubham.dynamicprogramming;

import java.util.Objects;
import java.util.PriorityQueue;

public class MinCostPathInMatrixUDLR {

    class Solution {
        int[][] A;
        int[] dx = new int[] { -1, 0, 1, 0 };
        int[] dy = new int[] { 0, 1, 0, -1 };
        Solution(int[][] A) {
            this.A = A;
        }
        int solve() {
            PriorityQueue<PosCost> minHeap = new PriorityQueue(A.length * A[0].length);
            Integer[][] cost = new Integer[A.length][A[0].length];
            PosCost posCost = new PosCost(0, 0, A[0][0]);
            minHeap.offer(posCost);
            while (!minHeap.isEmpty()) {
                posCost = minHeap.poll();
                for (int i = 0; i < 4; i++) {
                    int tempX = posCost.x + dx[i];
                    int tempY = posCost.y + dy[i];
                    if (isValidLocation(tempX, tempY)) {
                        if (cost[tempX][tempY] == null) {
                            cost[tempX][tempY] = posCost.cost + A[tempX][tempY];
                            minHeap.offer(new PosCost(tempX, tempY, cost[tempX][tempY]));
                        } else if (cost[tempX][tempY] > cost[posCost.x][posCost.y] + A[tempX][tempY]) {
                            cost[tempX][tempY] = posCost.cost + cost[tempX][tempY];
                            minHeap.remove(new PosCost(tempX, tempY));
                            minHeap.offer(new PosCost(tempX, tempY, cost[tempX][tempY]));
                        }
                    }
                }
            }
            return cost[A.length - 1][A[0].length -1];
        }

        boolean isValidLocation(int x, int y) {
            return x > -1 && x < A.length && y > -1 && y < A[0].length;
        }
    }

    class PosCost implements Comparable<PosCost> {
        int x;
        int y;
        int cost;

        PosCost(int x, int y) {
            this.x = x;
            this.y = y;
        }

        PosCost(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof PosCost)) return false;
            PosCost posCost = (PosCost) o;
            return this.x == posCost.x && this.y == posCost.y;
        }

        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public int compareTo(MinCostPathInMatrixUDLR.PosCost that) {
            return this.cost - that.cost;
        }
    }

    public static void main(String[] args) {
        int[][] A = {
                { 31, 100, 65, 12, 18 },
                { 10, 13, 47, 157, 6 },
                { 100, 113, 174, 11, 33 },
                { 88, 124, 41, 20, 140 },
                { 99, 32, 111, 41, 20 }
        };
        System.out.println(new MinCostPathInMatrixUDLR().new Solution(A).solve());
    }

}
