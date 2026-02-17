package code.shubham.trees.binarytree;

import java.util.ArrayList;
import java.util.List;

public class NeuronStrength {

    static List<Integer>[] g;
    static int[] weight, down, up, result;

    public static int[] neuronStrength(
            int n,
            int[] F,
            int[] T,
            int[] strongConnectivity) {

        g = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            g[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int u = F[i] - 1;
            int v = T[i] - 1;
            g[u].add(v);
            g[v].add(u);
        }

        weight = new int[n];
        for (int i = 0; i < n; ++i)
            weight[i] = (strongConnectivity[i] == 1) ? 1 : -1;

        down = new int[n];
        up = new int[n];
        result = new int[n];

        dfsDown(0, -1);
        dfsUp(0, -1);

        for (int i = 0; i < n; i++)
            result[i] = down[i] + up[i];

        return result;
    }

    static void dfsDown(int u, int p) {
        down[u] = weight[u];
        for (int n : g[u]) {
            if (n == p)
                continue;
            dfsDown(n, u);
            if (down[n] > 0)
                down[u] += down[n];
        }
    }

    static void dfsUp(int u, int p) {
        for (int n : g[u]) {
            if (n == p)
                continue;

            int contributionFromU = up[u] + down[u] - Math.max(0, down[n]);

            up[n] = Math.max(0, contributionFromU);
            dfsUp(n, u);
        }
    }

    public static void main(String[] args) {
        new NeuronStrength().neuronStrength(4, new int[] {1, 1, 1}, new int[] {2, 3, 4}, new int[] {0, 0, 1, 0});
    }
}
