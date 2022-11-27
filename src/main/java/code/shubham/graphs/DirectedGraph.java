package code.shubham.graphs;

public class DirectedGraph extends Graph {

    @Override
    public void addEdge(int u, int v) {
        addEdge(u, v, Integer.MIN_VALUE);
    }

    @Override
    public void addEdge(int u, int v, int cost) {
        insert(u, new Edge(v, cost), false);
    }
}
