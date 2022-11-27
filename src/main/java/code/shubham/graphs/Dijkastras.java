package code.shubham.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class    Dijkastras {

    private Map<Integer, List<Edge>> graph;

    private Map<Integer, List<Integer>> minPathCosts;

    public Dijkastras(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    // Optimal way to find SHORTEST Distance from a vertex to all
    public Map<Integer, Integer> Dijkastras (int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() != graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.cost);
                graph.get(e.v).forEach(edge -> {
                    if (!set.containsKey(edge.v))
                        pq.offer(new Edge(edge.v, e.cost + edge.cost));
                });
            }
        }
//        System.out.println(set);
        return set;
    }


    public void DijkastrasWithMinPathsInitialization(int s) {
        minPathCosts = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() != graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.cost);
                graph.get(e.v).forEach(edge -> {
                    if (!set.containsKey(edge.v)) {
                        List<Integer> costArrayOfCurrentNode = new ArrayList<>();
                        if (null != minPathCosts.get(e.v)) {
                            costArrayOfCurrentNode.addAll(minPathCosts.get(e.v));
                        }
                        costArrayOfCurrentNode.add(edge.cost);
                        minPathCosts.put(edge.v, costArrayOfCurrentNode);
                        pq.offer(new Edge(edge.v, e.cost + edge.cost));
                    }
                });
            }
        }
        System.out.println(set);
    }

    public Map<Integer, List<Integer>> getMinPathCosts() {
        return minPathCosts;
    }
}
