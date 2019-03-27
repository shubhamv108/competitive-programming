package code.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class KnightMovementOnAChessBoard {

     private class Solution {
         private int A, B;
         public int knight(int A, int B, int C, int D, int E, int F) {
             this.A = A;
             this.B = B;
             class Pos {
                 private int x;
                 private int y;
                 private int d;
                 private Pos (int x, int y, int d) {
                     this.x = x;
                     this.y = y;
                     this.d = d;
                 }
             }

             Queue<Pos> q = new LinkedList<>();
             Pos t;
             q.add(new Pos(C-1, D-1, 0));
             boolean[][] v = new boolean[A][B];
             while (!q.isEmpty()) {
                 t = q.poll();
                 if (t.x == E-1 && t.y == F-1) return t.d;
                 if (v[t.x][t.y]) continue;
                 v[t.x][t.y] = true;
                 if (isVisitable(v, t.x+2, t.y+1)) q.add(new Pos(t.x+2, t.y+1, t.d + 1));
                 if (isVisitable(v, t.x+1, t.y+2)) q.add(new Pos(t.x+1, t.y+2, t.d + 1));
                 if (isVisitable(v, t.x+2, t.y-1)) q.add(new Pos(t.x+2, t.y-1, t.d + 1));
                 if (isVisitable(v, t.x-2, t.y-1)) q.add(new Pos(t.x-2, t.y-1, t.d + 1));
                 if (isVisitable(v, t.x+1, t.y-2)) q.add(new Pos(t.x+1, t.y-2, t.d + 1));
                 if (isVisitable(v, t.x-1, t.y-2)) q.add(new Pos(t.x-1, t.y-2, t.d + 1));
                 if (isVisitable(v, t.x-1, t.y+2)) q.add(new Pos(t.x-1, t.y+2, t.d + 1));
                 if (isVisitable(v, t.x-2, t.y+1)) q.add(new Pos(t.x-2, t.y+1, t.d + 1));
             }
             return -1;
         }

         private boolean isVisitable (boolean[][] v, int x, int y) {
             return  x > -1 && x < A && y > -1 && y < B && !v[x][y];
         }
     }


    public static void main(String[] args) {
        System.out.println(new KnightMovementOnAChessBoard().new Solution().knight(8,8, 1, 1, 8, 8));
    }

}
