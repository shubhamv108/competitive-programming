package code.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.Arrays.fill;

public class BFS {

    private Map<Integer, List<Edge>> graph;

    private boolean[] visited;

    public BFS(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    public void BFS(int s) {
        boolean[] visited = new boolean[graph.size() + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[s] = q.offer(s);
        while(!q.isEmpty()) {
            s = q.poll();
            System.out.printf("%d ", s);
            graph.get(s).forEach(edge -> {
                if(!visited[edge.v]) visited[edge.v] = q.offer(edge.v);
            });
        }
    }

    public int shortestDistanceLengthBetweenTwoVertices(int s, int d) {
        if(graph == null || graph.isEmpty() || !graph.containsKey(s) || !graph.containsKey(d)) return 0;
        if(s == d) return 0;
        boolean[] visited = new boolean[graph.size() + 1];
        int[] level = new int[graph.size()];
        level[d] = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        level[s] = 0;
        while(!q.isEmpty()) {
            int a = q.poll();
            if(a == d) break;
            visited[a] = true;
            graph.get(a).forEach(edge -> {
                if (!visited[edge.v]) {
                    level[edge.v] = level[a] + 1;
                    q.offer(edge.v);
                }
            });
        }
        return level[d];
    }

    /**
     * Refer {@link Graph#Dijkastras(Map, int)}}
     * @param s
     * @return
     */
    public ArrayList<Integer> shortestDistanceLengthFromAVertexToAll(int s) {
        if(graph == null || graph.isEmpty() || !graph.containsKey(s)) return null;
        boolean[] visited = new boolean[graph.size()];
        Integer[] level = new Integer[graph.size()];
        Arrays.fill(level, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        level[s] = 0;
        while(!q.isEmpty()) {
            int a = q.poll();
            visited[a] = true;
            graph.get(a).forEach(edge -> {
                if (!visited[edge.v]) {
                    if(level[a] + 1 < level[edge.v]) {
                        level[edge.v] = level[a] + 1;
                        q.offer(edge.v);
                    }
                }
            });
        }
        return new ArrayList<>(Arrays.asList(level));
    }

    /**
     * Refer {@link Graph#Dijkastras(Map, int)}}
     * @param s
     * @return
     */
    public int[] leastCostPathFromAVertexToAll(int s) {
        if(null == graph || graph.isEmpty() || !graph.containsKey(s)) return null;
        boolean[] visited = new boolean[graph.size()];
        int[] cost = new int[graph.size()];
        fill(cost, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        cost[s] = 0;
        while(!q.isEmpty()) {
            int a = q.poll();
            visited[a] = true;
            graph.get(a).
                    stream().
                    forEach(edge -> {
                        if (!visited[edge.v]) {
                            if(cost[a] + edge.cost < cost[edge.v]) {
                                cost[edge.v] = cost[a] + edge.cost;
                                q.offer(edge.v);
                            }
                        }
                    });
        }
        return cost;
    }

}
