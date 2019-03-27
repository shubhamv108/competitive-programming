package code.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ColorfulStreet {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        int N;
        String[] line;
        int[][] a;
        int[][] cost;
        while (T-- > 0) {
            N = Integer.valueOf(br.readLine());
            a = new int[N][3];
            for (int i = 0; i < N; i++) {
                line = br.readLine().split(" ");
                for (int j=0; j < 3; j++) a[i][j] = Integer.valueOf(line[j]);
            }
            cost = new int[N][3];
            cost[0][0] = a[0][0];
            cost[0][1] = a[0][1];
            cost[0][2] = a[0][2];
            for (int i = 1; i < N; i++) {
                cost[i][0] = Math.min(a[i][0] + cost[i-1][1], a[i][0] + cost[i-1][2]);
                cost[i][1] = Math.min(a[i][1] + cost[i-1][0], a[i][1] + cost[i-1][2]);
                cost[i][2] = Math.min(a[i][2] + cost[i-1][0], a[i][2] + cost[i-1][1]);
            }
            System.out.println(Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2])));
        }
    }
}