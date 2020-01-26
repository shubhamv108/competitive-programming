package code.contestpractice.techgig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MakeAnArray {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] line = null;
        Integer[] B = null;
        while (t-- > 0) {
            int N = Integer.parseInt(br.readLine());
            line = br.readLine().split(" ");
            B = Arrays.stream(line).map(Integer::valueOf).toArray(Integer[]::new);

            int maxDiff[][] = new int [N][2];

            for (int i = 0; i < N; i++) {
                maxDiff[i][0] = maxDiff[i][1] = 0;
            }

            for (int i = 0; i < N - 1; i++) {
                maxDiff[i + 1][0] = Math.max(maxDiff[i][0], maxDiff[i][1] + Math.abs(1 - B[i]));
                maxDiff[i + 1][1] = Math.max(maxDiff[i][0] + Math.abs(B[i + 1] - 1),
                                             maxDiff[i][1] + Math.abs(B[i + 1] - B[i]));
            }

            System.out.println(Math.max(maxDiff[N - 1][0], maxDiff[N - 1][1]));
        }
    }

}
