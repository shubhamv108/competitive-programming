package code.dp.spoj;

import input.Reader;

import java.io.IOException;

class Solver12 {
    int M;
    int N;
    int cache[];

    Solver12(int m, int n, int[] cache) {
        this.M= m;
        this.N = n;
        this.cache = cache;
    }

    int solve(int idx, int giftsLeft) {
        return 0;
    }
}

public class BEHAPPY {
    public static void main(String[] args) {
        int[] cache = new int[200];
        for(int i=0;i < 200;i++) {
            cache[i] = -1;
        }
        Reader reader = new Reader();
        try {
            while(true) {
                int m = reader.nextInt();
                int[][] arr = new int[m][2];
                int n = reader.nextInt();
                if (m == 0 && n == 0) {
                    return;
                }
                for(int i=0;i<=m;i++) {
                    arr[i][0] = reader.nextInt();
                    arr[i][1] = reader.nextInt();
                }
                System.out.println(new Solver12(m, n, cache).solve(0, n));
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Invalid Input", e);
        }
    }
}
