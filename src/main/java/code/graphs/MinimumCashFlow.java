package code.graphs;

import java.util.stream.IntStream;

public class MinimumCashFlow {

    static int N = 3;

    public static void main(String[] args) {
        int[][] graph =
                {{0, 1000, 2000},
                 {0, 0, 5000},
                 {0, 0, 0}};
        new MinimumCashFlow().minCashFlow(graph);
    }

    void minCashFlowRec(int[] netAmount) {
        int maxCreditIndex = this.getMaximumIndex(netAmount);
        int maxDebitIndex  = this.getMinimumIndex(netAmount);

        if (netAmount[maxCreditIndex] == 0 && netAmount[maxDebitIndex] == 0)
            return;

        int min = Math.min(-netAmount[maxDebitIndex], netAmount[maxCreditIndex]);
        netAmount[maxCreditIndex] -= min;
        netAmount[maxDebitIndex]  += min;

        System.out.println(
                "Person " + maxCreditIndex + " pays " + min + " to " + "Person " + maxDebitIndex);

        minCashFlowRec(netAmount);
    }

    void minCashFlow(int graph[][]) {
        int[] netAmount = new int[N];
        for (int n = 0; n < N; n++)
            for (int i = 0; i < N; i++) {
                netAmount[n] -= graph[n][i];
                netAmount[n] += graph[i][n];
            }
        this.minCashFlowRec(netAmount);
    }

    int getMinimumIndex(int[] arr) {
        int minIndex = 0;
        for (int i = 1; i < N; i++)
            if (arr[i] < arr[minIndex])
                minIndex = i;
        return minIndex;
    }

    int getMaximumIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < N; i++)
            if (arr[i] > arr[maxIndex])
                maxIndex = i;
        return maxIndex;
    }

}
