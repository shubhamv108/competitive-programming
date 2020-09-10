package code.dynamicprogramming.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solver22 {
    int[] a;
    int N;
    int[][][] cache;
    static int totalCallsCount, cachedAnsReturned;

    Solver22(int[] a, int N, int[][][] cache) {
        this.a = a;
        this.N = N;
        this.cache = cache;
        totalCallsCount = 0;
        cachedAnsReturned = 0;
    }

    int solve(int idx, int whiteIdx, int blackIdx) {
        if(idx == N) return 0;
//        System.out.printf("idx=%d, whiteIdx=%d, blackIdx=%d\n", idx, whiteIdx, blackIdx);
//        totalCallsCount++;
        if(cache[idx][whiteIdx][blackIdx] != -1) {
//            cachedAnsReturned++;
            return cache[idx][whiteIdx][blackIdx];
        }
        int x, y, z;
        x = y = z = Integer.MAX_VALUE;
        if(a[idx] > a[blackIdx]) x = solve(idx + 1, whiteIdx, idx);
        if(a[idx] < a[whiteIdx]) y = solve(idx + 1, idx, blackIdx);
        z = 1 + solve(idx + 1, whiteIdx, blackIdx);
        return cache[idx][whiteIdx][blackIdx] = Math.min(x, Math.min(y, z));
    }
}

public class BORW {
    static int N;
    static int[] a = new int[200];
    static int[][][] cache = new int[200][200][200];

    static void initializeCache() {
        for(int i=0;i <= N-1;i++) {
            for (int j = 0; j <= N; j++)
                for (int k = 0; k <= N+1; k++) cache[i][j][k] = -1;
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(true) {
                N = Integer.parseInt(br.readLine());
                if (N == -1) return;
                initializeCache();
                int[] arr = new int[N+2];
                String[] line = br.readLine().split(" ");
                for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(line[i]);
                a[N] = Integer.MAX_VALUE;
                a[N+1] = Integer.MIN_VALUE;
                System.out.println(new Solver22(arr, N, cache).solve(0, N, N+1));
                System.out.printf("TOTAL CALLS: %d, Cached Ans: %d\n", Solver22.totalCallsCount, Solver22.cachedAnsReturned);
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Invalid Input", e);
        }
    }
}
