package code.shubham.graphs.trees.dp.rerooting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CountReverseEdges {
    public List<Integer> countReverseEdges(int n, List<Integer> F, List<Integer> T) {

        int[] result = new int[n];
        ArrayList<int[]>[] g = new ArrayList[n];

        for (int i = 0; i < n; ++i)
            g[i] = new ArrayList<>();

        int en = F.size();

        for (int i = 0; i < en; ++i) {
            int a = F.get(i) - 1;
            int b = T.get(i) - 1;

            g[a].add(new int[] { b, 0 } );
            g[b].add(new int[] { a, 1 } );
        }

        result[0] = recurse(g, 0, -1);
        recurse2(g, 0, -1, result);

        return IntStream.range(0, n)
                .mapToObj(i -> result[i])
                .toList();
    }

    int recurse(ArrayList<int[]>[] g, int u, int p) {
        int c = 0;

        for (int[] next : g[u]) {
            if (next[0] == p)
                continue;
            c += next[1] + recurse(g, next[0], u);
        }

        return c;
    }

    void recurse2(ArrayList<int[]>[] g, int u, int p, int[] result) {
        for (int[] next : g[u]) {
            if (next[0] == p)
                continue;

            if (next[1] == 0)
                result[next[0]] = result[u] + 1;
            else
                result[next[0]] = result[u] - 1;

            recurse2(g, next[0], u, result);
        }
    }

    void main() {
        System.out.println(new CountReverseEdges().countReverseEdges(3, Arrays.asList(2, 2), Arrays.asList(1, 3)));
    }
}
