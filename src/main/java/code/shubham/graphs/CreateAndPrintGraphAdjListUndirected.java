package code.shubham.graphs;

import java.util.*;

public class CreateAndPrintGraphAdjListUndirected {

    static Scanner sc = new Scanner(System.in);
    static Graph graph;

    public static void main(String[] args)
    {
        int t = Integer.parseInt(sc.next());
        while(t-- > 0) {
            int E = Integer.parseInt(sc.next());
            int V = Integer.parseInt(sc.next());
            int v = V;
            graph = new Graph();
            while(v-- > 0) {
                int x = Integer.parseInt(sc.next());
                int y = Integer.parseInt(sc.next());
                graph.insert(x, new Edge(y, 0), true);
            }
            graph.print();
        }
    }
}