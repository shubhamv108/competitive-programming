package code.matrices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintMatrixSpirally {

    public static void printMatrixSpirally(int[][] a) {
        int R = a.length - 1;
        int C = a[0].length - 1;
        int r = 0;
        int c = 0;
        int i;

        while (r <= R && c <= C) {
//            if (r <= R) {
            for (i = c; i <= C; i++) {
                System.out.print(a[r][i] + " ");
            }
            r++;
//            }
//            if (c <= C) {
            for (i = r; i <= R; i++) {
                System.out.print(a[i][C] + " ");
            }
            C--;
//            }
            if (r <= R) {
                for (i = C; i >= c; i--) {
                    System.out.print(a[R][i] + " ");
                }
                R--;
            }
            if (c <= C) {
                for (i = R; i >= r; i--) {
                    System.out.print(a[i][c] + " ");
                }
                c++;
            }
        }
    }

    
    // ReDo
    static class Solution {
        public static List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<>();
            int n = matrix.length, m = matrix[0].length, r = 0, R = n, c = 0, C = m;
            boolean flag = false;
            while (result.size() < n*m) {
                for (int i = c; i != C; i = i + (!flag ? 1 : -1)) {
                    result.add(matrix[r][i]);
                }
                for (int i = r; i != R; i = i + (!flag ? 1 : -1)) {
                    result.add(matrix[i][C-1]);
                }
                int t = r;
                r = R;
                R = t;
                t = c;
                c = C;
                C = t;
                flag = !flag;
                if (flag) {
                    c--;
                    R++;
                } else {
                    c++;
                    R--;
                }
            }
            return result;
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
//        int r = 3, c = r, a[][] = new int[r][c];
//        for(int i=0;i<r;i++) {
//            for(int j=0;j<c;j++) {
//                a[i][j] = Integer.parseInt(sc.next());
//            }
//        }
        int a[][] = {
            {1,  2,  3,  4,  5,  6},
            {7,  8,  9,  10, 11, 12},
            {13, 14, 15, 16, 17, 18}
        };
//        printMatrixSpirally(a);
        PrintMatrixSpirally.Solution.spiralOrder(a);
    }

    public static ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> ll = new ArrayList<>();
            for (int j=0; j<A; j++) ll.add(0);
            l.add(ll);
        }
        int R = A - 1;
        int C = A - 1;
        int r = 0;
        int c = 0;
        int k = 1;
        while (r <= R && c <= C) {
            for (int i=c; i <= C; i++ )      l.get(r).set(i, k++);
            r++;
            for (int i=r; i<=R; i++)         l.get(i).set(C, k++);
            C--;
            if (r <= R) {
                for (int i = C; i >= c; i--) l.get(R).set(i, k++);
                R--;
            }
            if (c <= C) {
                for (int i = R; i >= r; i--) l.get(i).set(c, k++);
                c++;
            }
        }
        return l;
    }

//    public static void main(String[] args) {
//        System.out.println(generateMatrix(3));
//    }
}
