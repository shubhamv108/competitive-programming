package code.dynamicprogramming.spoj;

import input.Reader;

import java.io.IOException;

class Solver1 {
    int N;
    int[] a;
    int[] cache;
    // ArrayList
    Solver1(int[] a, int n, int[] cache) {
        this.a = a;
        this.N = n;
        this.cache = cache;
    }

    long solve(int idx) {
        if(idx == N) return 0l;
//        return Math.max(a[idx] + solve(idx+2));
        return 0;

    }
}

public class DCEPC501 {
    public static void main(String[] args) {
        Reader reader = new Reader();
        try {
            int t = reader.nextInt();
            while(t-- > 0) {
                int n = reader.nextInt();
                int[] arr = new int[n];
                for(int i=0;i<n;i++) {
                    arr[i] = reader.nextInt();
                }
//                for(int i=0;i < 200;i++) {
//                    cache[i] = -1;
//                }
//                System.out.println(new Solver1(arr, n, cahce).solve();
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Invalid Input", e);
        }
    }
}
