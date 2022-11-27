package code.shubham.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

import java.util.*;
public class Dijkstras1 {
    void printSingleSourceShortestDistance(Map<Integer, ArrayList<Edge1>> g, int s) {
        PriorityQueue<Edge1> pq = new PriorityQueue<>();
        int[] cost = new int[g.size() + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        pq.offer(new Edge1(s, 0));
        while(!pq.isEmpty()) {
            Edge1 e = pq.poll();
            if(cost[e.v] == Integer.MAX_VALUE) {
                cost[e.v] = e.w;
                g.get(e.v).stream().forEach(edge -> {
                    if (cost[edge.v] == Integer.MAX_VALUE) {
                        pq.offer(new Edge1(edge.v, e.w + edge.w));
                    }
                });
            }
        }
        Arrays.stream(cost).forEach(e -> System.out.print(e));
        System.out.println();
    }
}


class Edge1 implements Comparable<Edge1> {
    public int v;
    public int w;
    public Edge1(int v, int w){ this.v = v; this.w = w; }
    public int compareTo(Edge1 o){
        return this.w - o.w;
    }
}
