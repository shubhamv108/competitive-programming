package code.shubham.recursion;

import java.util.Scanner;

public class PrintAllMatrixPaths {

    static void printAllMatrixPathsHelper(int[][] a, int r, int c, int R, int C) {
        System.out.printf("%d ", a[r][c]);
        if(r == R - 1 && c == C - 1) {
            System.out.printf("\n");
            return;
        }
        if(r < R-1) {
            printAllMatrixPathsHelper(a, r + 1, c, R, C);
            if(r == 0 && c == 0) {
                System.out.printf("%d ", a[r][c]);
            }
        }
        if(c < C-1) {
            printAllMatrixPathsHelper(a, r, c +1, R, C);
        }
    }

    static void printAllMatrixPaths(int[][] m) {
        printAllMatrixPathsHelper(m, 0, 0, m.length, m[0].length);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        while(t-- > 0) {
            int r = Integer.parseInt(sc.next());
            int c = Integer.parseInt(sc.next());
            int[][] a = new int[r][c];
            for(int i=0;i<r;i++) {
                for(int j=0;j<c;j++) {
                    a[i][j] = Integer.parseInt(sc.next());
                }
            }
            printAllMatrixPaths(a);
            System.out.printf("\n");
        }
    }
}
