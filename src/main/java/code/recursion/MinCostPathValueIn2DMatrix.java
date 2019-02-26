package code.recursion;

import java.util.Scanner;

public class MinCostPathValueIn2DMatrix {

    static int minCostPathValueHelper(int[][] a, int r, int c, int R, int C, int s) {
        if(c == C-1 && r == R-1) {
            return s + a[r][c];
        }
        int u = Integer.MAX_VALUE, v = Integer.MAX_VALUE, w = Integer.MAX_VALUE;
        if(r < R-1) {
            u = minCostPathValueHelper(a, r + 1, c, R, C, s + a[r][c]);
        }
        if(c < C - 1) {
            v = minCostPathValueHelper(a, r, c + 1, R, C, s + a[r][c]);
        }
        if(r < R-1 && c < C - 1) {
            w = minCostPathValueHelper(a, r + 1, c + 1, R, C, s + a[r][c]);
        }
        return Math.min(u, Math.min(v, w));

    }

    static int minCostPathValue(int a[][]) {
        return minCostPathValueHelper(a, 0, 0, a.length, a[0].length, 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.next());
        while(T-- > 0) {
            int R = Integer.parseInt(sc.next());
            int C = Integer.parseInt(sc.next());
            int[][] a = new int[R][C];
            for(int i=0;i<R;i++) {
                for(int j=0;j<C;j++) {
                    a[i][j] = Integer.parseInt(sc.next());
                }
            }
            System.out.printf("%d", minCostPathValue(a));
        }
    }
}
