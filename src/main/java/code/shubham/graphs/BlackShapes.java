package code.shubham.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BlackShapes {

    class Solution {
        private Stack<Pos> s = new Stack<>();
        private ArrayList<String> A;
        private int N, M;
        private boolean[][] visited;
        private Pos p;
        public int black(ArrayList<String> A) {
            this.A = A;
            N = A.size();
            M = A.stream().findFirst().get().length();
            visited = new boolean[N][M];
            int count = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (A.get(i).charAt(j) == 'X' && !visited[i][j])
                        count += visit (i, j);
            return count;
        }

        private class Pos {
            int i;
            int j;
            Pos(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        private int visit (int i, int j) {
            s.push(new Pos(i, j));
            while (!s.isEmpty()) {
                p = s.pop();
                visited[p.i][p.j] = true;
                if (isVisitable(p.i - 1, p.j)) s.push(new Pos(p.i-1, p.j));
                if (isVisitable(p.i, p.j - 1)) s.push(new Pos(p.i, p.j - 1));
                if (isVisitable(p.i + 1, p.j)) s.push(new Pos(p.i + 1, p.j));
                if (isVisitable(p.i, p.j + 1)) s.push(new Pos(p.i, p.j + 1));
            }
            return 1;
        }

        private boolean isVisitable (int i, int j) {
            if (i > -1 && i < N && j > -1 && j< M && A.get(i).charAt(j) == 'X' && !visited[i][j]) return true;
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new BlackShapes().new Solution().black(new ArrayList<>(Arrays.asList("XXX", "XXX", "XXX"))));
    }

}
