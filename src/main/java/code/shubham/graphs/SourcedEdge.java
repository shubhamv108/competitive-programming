package code.shubham.graphs;

public class SourcedEdge implements Comparable<SourcedEdge> {

    public int src;
    public int dst;
    public int cost;

    @Override
    public int compareTo(SourcedEdge o) {
        return this.cost = o.cost;
    }
}
