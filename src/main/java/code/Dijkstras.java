package code;

import java.util.ArrayList;
import java.util.PriorityQueue;

import java.util.*;
public class Dijkstras
{
    void printSingleSourceShortestDistance(Map<Integer, ArrayList<Edge>> g, int s)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] cost = new int[g.size() + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(cost[e.v] == Integer.MAX_VALUE) {
                cost[e.v] = e.w;
                g.get(e.v).stream().forEach(edge -> {
                    if (cost[edge.v] == Integer.MAX_VALUE) {
                        pq.offer(new Edge(edge.v, e.w + edge.w));
                    }
                });
            }
        }
        Arrays.stream(cost).forEach(e -> System.out.print(e));
        System.out.println();
    }
}


class Edge implements Comparable<Edge>
{
    int v;
    int w;
    Edge(int v, int w){ this.v = v; this.w = w; }
    public int compareTo(Edge o){
        return this.w - o.w;
    }
}
