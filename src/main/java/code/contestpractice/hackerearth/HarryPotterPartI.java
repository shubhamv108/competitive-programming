package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.max;

public class HarryPotterPartI {
    static int h, w;
    static int[][] a = new int[200][200];
    static int[][] cache = new int[200][200];
    static int tripHelper(int r, int c, int ans) {
        if(r == h) return ans;
        if(c < 0 || c == w) return 0;
        if(cache[r][c] != -1) return cache[r][c];
        return cache[r][c] =
                max(tripHelper(r+1, c-1, ans+a[r][c]),
                    max(tripHelper(r+1, c, ans+a[r][c]),
                        tripHelper(r+1, c+1, ans+a[r][c])));
    }
    static int maxStones() {
        int ret = 0;
        for(int i=0;i<w;i++) {
            ret = max(ret, tripHelper(0, i, 0));
        } return ret;
    }

    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] line;
        while(t-- > 0) {
            line = br.readLine().split(" ");
            h = Integer.parseInt(line[0]);
            w = Integer.parseInt(line[1]);
            for(int i=0;i<h;i++) {
                line = br.readLine().split(" ");
                for(int j=0;j<w;j++) {
                    a[i][j] = Integer.parseInt(line[j]);
                    cache[i][j] = -1;
                }
            }

            System.out.println(maxStones());
            for(int i=0;i<h;i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(cache[i][j] + " ");
                }
                System.out.println(" ");
            }
        }
    }
}
