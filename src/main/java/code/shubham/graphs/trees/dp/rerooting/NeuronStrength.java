package code.shubham.graphs.trees.dp.rerooting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NeuronStrength {

    public List<Integer> getNeuronStrengths(int n, int[] A, int[] B, final int[] SC) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer>[] g = new ArrayList[n];
        IntStream.range(0, n).forEach(i -> g[i] = new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            int a = A[i] - 1, b = B[i] - 1;
            g[a].add(b);
            g[b].add(a);
        }

        int[] c = new int[n];
        IntStream.range(0, n).forEach(i -> c[i] = SC[i] == 1 ? 1 : -1);
        int[] down = new int[n], up = new int[n];

        recurseDown(0, -1, down, c, g);
        recurseUp(0, -1, up, down, g);

        for (int i = 0; i < n; ++i)
            result.add(up[i] + down[i]);

        return result;
    }

    void recurseDown(int u, int p, int[] down, int[] c, ArrayList<Integer>[] g) {
        down[u] = c[u];
        for (int v : g[u]) {
            if (v == p)
                continue;
            recurseDown(v, u, down, c, g);
            if (down[v] > 0)
                down[u] += down[v];
        }
    }

    void recurseUp(int u, int p, int[] up, int[] down, ArrayList<Integer>[] g) {
        for (int v : g[u]) {
            if (v == p)
                continue;

            int count = up[u] + down[u] - Math.max(0, down[v]);
            up[v] = Math.max(0, count);
            recurseUp(v, u, up, down, g);
        }
    }

    static void main(String[] args) {
        System.out.println(new NeuronStrength().getNeuronStrengths(
                4,
                new int[] { 1, 1, 1 },
                new int[] { 2, 3, 4 },
                new int[] {0, 0, 1, 0}));
    }

}
