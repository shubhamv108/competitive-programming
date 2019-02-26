package code.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSort {

    private Map<Integer, List<Edge>> graph;

    private boolean[] visited;

    private int[] inDegree = new int[graph.size() + 1];

    public TopologicalSort(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    private void updateInDegree() {
        for (Integer e : graph.keySet())
            for (Edge e1 : graph.get(e))
                inDegree[e1.v]++;
    }

    void topSort() {
        visited = new boolean[graph.size() + 1];
        inDegree = new int[graph.size() + 1];
        int countOfVisited = 0;
        updateInDegree();
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<graph.size();i++) { if (inDegree[i] == 0)  q.offer(i); }
        while(!q.isEmpty()) {
            int a = q.poll();
            visited[a] = true;
            countOfVisited++;
            System.out.printf("%d ", a);
            for (Edge e : graph.get(a)) if (!visited[e.v] && --inDegree[e.v] == 0) q.offer(e.v);
        }
        if(countOfVisited != graph.size()) {
            System.out.println("Topological Sort not possible::There exists a cycle");
            return;
        }
    }

}
