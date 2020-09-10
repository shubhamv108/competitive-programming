package code.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinCostToConvertNToMByAddingEvenDivisor {

    static int solve(int N, int M){
        Integer[] minCosts = new Integer[M - N + 1];
        int minCost = getMinimumCost(N, N, M, minCosts);
        if (minCost == Integer.MAX_VALUE) minCost = -1;
        return minCost;
    }

    static int getMinimumCost(int cur, int N, int M, Integer[] minCosts) {
        if (cur > M) return Integer.MAX_VALUE;
        if (cur == M) return 0;
        if (minCosts[cur - N] != null) return minCosts[cur - N];
        Integer minCost = Integer.MAX_VALUE;
        for (int evenDivisor = 2; evenDivisor * 2 <= cur; evenDivisor += 2) {
            if (cur % evenDivisor == 0) {
                int cost = getMinimumCost(cur + evenDivisor, N, M, minCosts);
                if (cost != Integer.MAX_VALUE) minCost = Math.min(minCost, (cur / evenDivisor) + cost);
            }
        }
        return minCosts[cur - N] = minCost;
    }

    /**
     * Input
     * 1
     * 6 24
     *
     * Output
     * 10
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < T; i++) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
            int result = solve(N, M);
            System.out.println(result);
        }
        br.close();
    }
}