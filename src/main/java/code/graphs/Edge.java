package code.graphs;

public class Edge implements Comparable<Edge> {

    public int v;
    public int cost;
    public Edge(int v, int w){
        this.v = v;
        this.cost = w;
    }

    public int compareTo(Edge o){
        return this.cost - o.cost;
    }

    @Override
    public String toString() { return "" + v + "(" + cost + ") "; }
}
