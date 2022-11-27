package code.shubham.backtracking;

import java.util.*;

public class Solution {

    static int[][] arr;
    static int R;
    static int C;
    // static long[][][] cache;

    public static int countPathsFromOriginToEndWithSumHelper(int r, int c, int sum) {
        if(r == R-1 && c == C-1) {
            if(sum - arr[r][c] == 0)
                return 1;
            return 0;
        }
        if(r == R || c == C) return 0;
        // if(cache[r][c][sum] != Integer.MAX_VALUE) return cache[r][c][sum];
        return countPathsFromOriginToEndWithSumHelper(r+1, c, sum - arr[r][c]) +
                countPathsFromOriginToEndWithSumHelper(r, c+1, sum - arr[r][c]);
    }

    public static int countPathsFromOriginToEndWithSum(int a[][], int sum)
    {
        R = a.length;
        C = a[0].length;
        arr = a;
        //cache = new cache[R][C][];
        return countPathsFromOriginToEndWithSumHelper(0, 0, sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        int mat[][] = new int[r][c];

        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++)
                mat[i][j] = sc.nextInt();

        int q = sc.nextInt();
        while(q-- > 0){
            int sum = sc.nextInt();
            System.out.printf("%d\n", countPathsFromOriginToEndWithSum(mat, sum));
        }
    }
}
