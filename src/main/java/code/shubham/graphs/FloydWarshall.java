package code.shubham.graphs;

import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Optional.ofNullable;

/**
 * All Pair shortest path
 */
public class FloydWarshall {

    private int[][] graph;
    private int V;

    public FloydWarshall(int[][] graph) {
        this.graph = graph;
        V = graph.length;
    }

    public void FLOYDWARSHALL(int startVertex) {
        if ( graph[0].length != V ) return;
        for ( int k = 0; k < V; k++ )
            for ( int i = 0; i < V; i++ )
                for ( int j = 0; j < V; j++ )
                    if ( graph[i][k] + graph[k][j] < graph[i][j] )
                        graph[i][j] = graph[k][j] + graph[i][k];
    }

    public void FLOYDWARSHALL(HashMap<Integer, List<Edge>> graph, int startVertex) {
        int V = graph.size();
        if ( graph.isEmpty() ) {
            return;
        }
        for ( int k = 0; k < V; k++ )
            for ( int i = 0; i < V; i++ )
                for ( int j = 0; j < V; j++ ) {

                    List<Edge> ile = ofNullable(graph.get(i)).orElse(null);
                    List<Edge> kle = ofNullable(graph.get(k)).orElse(null);

                    Edge ij = new Edge(j, MAX_VALUE);
                    Edge ik = new Edge(k, MAX_VALUE);
                    Edge kj = new Edge(j, MAX_VALUE);
                    boolean ijl, ikl, kjl;
                    ijl = ikl = kjl = false;

                    if (null != ile) {
                        for (Edge e : ile) {
                            if (j == e.v) { ij = e; ijl = true; }
                            if (k == e.v) { ik = e; ikl = true; }
                        }
                    }

                    if (null != kle) {
                        for (Edge e : ile) {
                            if (j == e.v) { kj = e; kjl = true; }
                        }
                    }

                    if (ij.cost > ik.cost + kj.cost) {
                        if (!ijl) {
                            ile.add(ij);
                        }
                        ij.cost = ik.cost + kj.cost;
                    }

                }
    }

}
