package code.shubham.graphs;

import java.util.*;

/**
 * ToDo
 *
 */
public class KnightOnAChessBoardWithRook {

    /**
     * Solution answer to be verified
     */
    class Solution {
        class Pos {
            int r;
            int c;
            Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        int[] row;
        int[] col;
        int totalPos;
        Set<String> visited = new HashSet<>();

        Solution(int rows, int cols) {
            row = new int[rows];
            col = new int[cols];
        }

        void intializePosCountForKnight(int i, int j) {
            List<Pos> moves = Arrays.asList(
                    new Pos(2, -1),
                    new Pos(2, 1),
                    new Pos(-2, -1),
                    new Pos(-2, 1),
                    new Pos(1, 2),
                    new Pos(1, -2),
                    new Pos(-1, 2),
                    new Pos(-1, -2)
            );
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(i, j));
            visited.add(i + "-" + j);
            while(!q.isEmpty()) {
                Pos t = q.poll();
                for (Pos m : moves) {
                    int nextRow = t.r + m.r;
                    int nextCol = t.c + m.c;
                    if (isVisitable(nextRow, nextCol, visited)) {
                        q.offer(new Pos(nextRow, nextCol));
                        visited.add(nextRow + "-" + nextCol);
                        row[nextRow - 1]++;
                        col[nextCol - 1]++;
                        totalPos++;
                    }
                }

            }
        }

        boolean isVisitable(int i, int j, Set<String> visited) {
            return i > 0 && i <= row.length && j > 0 && j <= col.length && !visited.contains(i + "-" + j);
        }

        int queryCountWithRookPos(int i, int j) {
            int result = totalPos - row[i - 1] - col[j - 1];
            if (visited.contains(i + "-" + j))
                result++;
            return result;
        }

    }

}
