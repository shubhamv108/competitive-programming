package code.shubham.graphs;

import java.util.LinkedList;
import java.util.Queue;

/** ToDo */
public class LargestIslandInA2DMatrix {

    static class Solution {

        int[][] A;
        int land;
        int VISITED  = -1;
        Solution(int[][] arr, int land) {
            this.A = arr;
            this.land = land;
        }

        int solve() {
            int maxSize = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    if (isSafeToVisit(i, j)) {
                        maxSize = Math.max(maxSize, bfsVisitAndGetSize(i, j));
                    }
                }
            }
            return maxSize == Integer.MIN_VALUE ? 0 : maxSize;
        }

        static class Position {
            int x;
            int y;
            Position(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        int bfsVisitAndGetSize(int x, int y) {
            Queue<Position> queue = new LinkedList<Position>();
            queue.offer(new Position(x, y));
            while (!queue.isEmpty()) {
                Position temp = queue.poll();
                visit(temp.x, temp.y);
                for (int i = temp.x -1; i <= temp.x + 1; i++) {
                    for (int j = temp.y -1; j <= temp.y + 1; j++) {
                        if (isSafeToVisit(i, j)) {
                            queue.offer(new Position(i, j));
                        }
                    }
                }
            }
            return 0;
        }

        void visit(int x, int y) {
            A[x][y] = VISITED;
        }

        boolean isSafeToVisit(int x, int y) {
            return isInRange(x, y) && !isVisited(x, y) && isLand(x, y);
        }

        boolean isInRange(int x, int y) {
            return x > -1 && x < A.length && y > -1 && y < A[y].length;
        }

        boolean isVisited(int x, int y) {
            return A[x][y] == VISITED;
        }

        boolean isLand(int x, int y) {
            return A[x][y] == land;
        }

    }

    public static void main(String[] args) {

    }

}
