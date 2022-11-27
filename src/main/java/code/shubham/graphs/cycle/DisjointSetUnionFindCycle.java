package code.shubham.graphs.cycle;

import code.shubham.graphs.SourcedEdge;

import java.util.stream.IntStream;

public class DisjointSetUnionFindCycle {

    private SourcedEdge[] edges;
    private int[] parent;

    public DisjointSetUnionFindCycle(SourcedEdge[] edges) {
        this.edges = edges;
        parent = IntStream.range(0, edges.length).map(e -> -1).toArray();
    }

    public boolean isCycle() {
        for (SourcedEdge e : edges) {
            int x = findSubset(e.src);
            int y = findSubset(e.dst);
            if (x == y) return true;
            union(x, y);
        }
        return false;
    }

    private void union(int x, int y) {
        int xset = findSubset(x);
        int yset = findSubset(y);
        parent[xset] = yset;
    }

    private int findSubset(int i) {
        if (parent[i] == -1) return i;
        return findSubset(parent[i]);
    }

}
