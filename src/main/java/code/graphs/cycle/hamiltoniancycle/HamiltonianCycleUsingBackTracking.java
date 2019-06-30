package code.graphs.cycle.hamiltoniancycle;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * NP Hard
 *
 * O(n!) - Brute Force (Backtracking)
 */
public class HamiltonianCycleUsingBackTracking {

    private int graph[][];
    private int V;

    public HamiltonianCycleUsingBackTracking(int graph[][]) {
        this.graph = graph;
        this.V = graph.length;
    }

    public boolean isCycle(int startVertex, boolean printCycle) {
        int[] path = new int[V];
        path[0] = startVertex;
        if (hamCycle(path, 1)) {
            if (printCycle)
                Arrays.stream(path).forEach(e -> out.println(e + " "));
            return true;
        }
        return false;
    }


    public boolean hamCycle(int[] path, int pi) {

        if (pi == V) {
            if (graph[path[pi - 1]][path[0]] == 1) {
                return true;
            } else {
                return false;
            }
        }

        for ( int i = 1; i < V; i++ ) {
            if (isSafe(i, path, pi))
                path[pi++] = i;
                if (hamCycle(path, pi)) {
                    return true;
                }
                path[--pi] = -1;
        }
        return false;

    }

    public boolean isSafe(int v, int[] path, int pi) {
        if (graph[path[pi]][path[v]] == 0) return false;
        return Arrays.stream(path).anyMatch(e -> e == v);
    }

}
