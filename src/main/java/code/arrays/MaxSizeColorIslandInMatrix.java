package code.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaxSizeColorIslandInMatrix {


    private static boolean[][] visited;

    private static int[][] a = {
            {2, 2, 2, 2},
            {0, 1, 2, 1},
            {0, 1, 1, 1}
    };
    private static int I = 3, J = 4;

    private static class Largest {
        int size = Integer.MIN_VALUE;
        List<Integer> colors = null;

    }

    private static Stack<Pos> stack = new Stack<>();
    public static void main(String[] args) {
        Largest largest = new Largest();
        visited = new boolean[3][4];
        for (int i=0;i<3;i++) {
            for (int j=0;j<4; j++) {
                if (!visited[i][j]) {
                    int size = iterative (i, j);// recurse(a[i][j], i, j);
                    if (size > largest.size) {
                        largest.size = size;
                        largest.colors = new ArrayList<>();
                        largest.colors.add(a[i][j]);
                    } else if (size == largest.size) {
                        largest.colors.add(a[i][j]);
                    }
                }
            }
        }
        System.out.println(largest.colors);
    }

    private static int recurse (int v, int i, int j) {
        if (i == -1 || j == -1 || i == I || j == J) return 0;
        if (v != a[i][j] || visited[i][j]) return 0;
        visited[i][j] = true;
        return  1 + recurse (v, i, j+1) +
                    recurse (v, i-1, j) +
                    recurse (v, i, j-1) +
                    recurse (v, i+1, j);
    }

    private static class Pos {
        int i, j;
        Pos(int i, int j) { this.i = i; this.j = j; }
    }

    private static int iterative (int i, int j) {
        int size = 0;
        stack.push(new Pos(i, j));
        while (!stack.empty()) {
            Pos p = stack.pop();
            visited[p.i][p.j] = true;
            size += 1;
            if ( isValidForVisit(a[i][j], p.i - 1, p.j ) )     stack.push(new Pos(p.i-1, p.j));
            if ( isValidForVisit(a[i][j],    p.i,  p.j - 1 ) ) stack.push(new Pos(p.i, p.j - 1));
            if ( isValidForVisit(a[i][j],    p.i,  p.j + 1 ) ) stack.push(new Pos(p.i, p.j + 1));
            if ( isValidForVisit(a[i][j], p.i + 1, p.j ) )     stack.push(new Pos(p.i + 1, p.j));
        }
        return size;
    }

    private static boolean isValidForVisit (int v, int i, int j) {
        return isValidPos(i, j) && !visited[i][j] && v == a[i][j];
    }

    private static boolean isValidPos (int i, int j) {
        return i > -1 && j > -1 && i < I && j < J;
    }


}
