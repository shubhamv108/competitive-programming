package code.shubham.recursion;

import java.util.Scanner;

public class HasSumPath {

    static int R, C;

    static boolean hasSumPathHelper(int[][] a, int r, int c, int s, int sum) {
        if(r == R-1 && c == C-1) {
            return (s + a[r][c]) == sum;
        }
        if(r < R-1) {
            if(hasSumPathHelper(a, r + 1, c, s + a[r][c], sum)) {
                return true;
            }
        }
        if(c < C-1) {
            return hasSumPathHelper(a, r, c + 1, s + a[r][c], sum);
        }
        return false;
    }

    static boolean hasSumPath(int a[][], int sum)
    {
        return hasSumPathHelper(a, 0, 0, 0, sum);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.next());
        while(T-- > 0) {
            R = Integer.parseInt(sc.next());
            C = Integer.parseInt(sc.next());
            int[][] arr = new int[R][C];
            for(int i=0;i<R;i++) {
                for(int j=0;j<C;j++) {
                    arr[i][j] = Integer.parseInt(sc.next());
                }
            }
            int q = Integer.parseInt(sc.next());
            while(q-- > 0) {
                int s = Integer.parseInt(sc.next());
                System.out.println(hasSumPath(arr, s));
            }
        }
    }
}
