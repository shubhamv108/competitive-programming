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

    public class Solution2 {
        int[][] directions = new int[][] {{-2, -1}, {-2, 1}, {1, 2}, {-1, 2}, {2, 1}, {2, -1}, {1, -2}, {1, 2}};
        public int knight(int A, int B, int C, int D, int E, int F) {
            int result = 0;
            if (C == E && E == F) return 0;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {C, D, 0});
            boolean[][] visited = new boolean[A][B];
            while (!q.isEmpty()) {
                int size = q.size();
//                while (size-- > 0) {
                int[] p = q.poll();
                for (int[] dir : directions) {
                    int r = p[0] + dir[0], c = p[1] + dir[1];
                    if (r < 1 || c < 1 || r > A || c > B) continue;
                    if (r == E && c == F) return p[2] + 1;
                    if (!visited[r-1][c-1]) {
                        q.offer(new int[] {r, c, p[2] + 1});
                        visited[r-1][c-1] = true;
                    }
                }
//                }
//                result++;
            }
            return -1;
        }
    }



    public static void main(String[] args) {
        System.out.println(new KnightMovementOnAChessBoard().new Solution2().knight(13,17, 8, 9, 4, 6));
    }

}
//Go
//        Type system (Statically typed)
//        Interfaces - custom type with collection of method
//        Empty Interface - no methods in interface all types implement empty iterface
//        Composition vs Inheritance (Struct Fields vs Embeds)
//        Function Methods (like http.HandlerFunc)
//        Pointer Receiver vs Value Receiver
//        Channel / Buffered vs UnBuffered channel ? An unbuffered channel is used to perform synchronous communication between goroutines
//                                                      while a buffered channel is used for perform asynchronous communication.

//
//
//        Type system (Dynamically typed)
//        Abstract Class and Abstract Methods
//        Composition vs Inheritance
//        Decorators - modify behaviour of a method
//        Properties -
//        StaticMethods vs Class Methods
//        Metaclasses -
//        Magic functions - __ at start and end, generally invoked internally
//        Super method
//        context manager - with -> class with method __enter__, __end__
//
//xx
//        Abstract class vs interface
    //    JVM
    //    Access modifiers: public private protected(package and subclass) default(package)
    //    Static methods
//        Synchronized method
//        Composition
//
//
//
//green thread
//container vs vitual machines (VMs virtualize an entire machine down to the hardware layers and
//                               containers only virtualize software layers above the operating system level.)
//thread vs process
//            innterprocess conmmunican (shared memory, messge passing)
//
//
//
//
//3 special integers
//prefixMatrix, subMatrx
//
//
//
//ooops + coding
//
//
//HM - design - requiremet gathering, take feedback