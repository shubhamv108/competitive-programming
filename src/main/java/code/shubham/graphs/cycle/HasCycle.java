package code.shubham.graphs.cycle;

import code.shubham.graphs.Edge;

import java.util.List;
import java.util.Map;


/**
 * DFS based approach for Detecting Cycle in graph.
 */
public class HasCycle {

    private int V;
    private Map<Integer, List<Edge>> graph;

    public HasCycle ( Map < Integer, List < Edge > > graph ) {
        this.graph = graph;
        this.V = graph.size();
    }

    public boolean hasCycle () {
        boolean[ ] visited = new boolean [ V ];
        boolean[ ] recursioStack = new boolean [ V ];
        for ( int i = 0; i < V; i++ ) {
            if ( isCyclic ( i, visited, recursioStack ) ) return true;
        }
        return false;
    }

    private boolean isCyclic(int i, boolean[] visited, boolean[] recurStack) {

        if (recurStack[i]) { return true; }

        if (visited[i]) { return false; }

        visited[i] = true;
        recurStack[i] = true;

        boolean cyclic = graph.get(i).stream().anyMatch(e -> isCyclic(e.v, visited, recurStack));

        if (cyclic) return true;

        recurStack[i] = false;

        return false;

    }

}
