package code.graphs;

import code.graphs.util.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Integer, List<Edge>> graph = new HashMap<>();

    private boolean[] visited;

    public void addEdge(int u, int v) {
        insert (u, new Edge(v, Integer.MIN_VALUE), true);
    }

    public void addEdge(int u, int v, int cost) {
        insert (u, new Edge(v, cost), true);
    }

    public void insert(Integer v, Edge e, boolean doReverseMapping) {
        if(graph.containsKey(v)) { graph.get(v).add(e); }
        else {
            ArrayList<Edge> l = new ArrayList<>();
            l.add(e);
            graph.put(v, l);
        }
        if(doReverseMapping) insert(e.v, new Edge(v, e.cost), !doReverseMapping);
    }

    public void print() {
        for (Map.Entry e : graph.entrySet()) {
            System.out.printf("%d : ", e.getKey());
            List<Edge> l = (List<Edge>) e.getValue();
            l.stream().forEach(n -> System.out.printf("%s", n.toString()));
            System.out.printf("\n");
        }
        System.out.printf("\n");

    }

    public void BFS(int startVertex) {
        new BFS(graph).BFS(startVertex);
    }

    public void DFS() {
        new DFS(graph).DFS();
    }

    public int connectedComponentsCount(){
        return new DFS(graph).connectedComponentsCount();
    }

    public void dfs() {
        new DFS(graph).dfs();
    }

    public void printDFSPath(int startVertex, int d) {
        new DFS(graph).printDFSPath(startVertex, d);
    }

    public int shortestDistanceLengthBetweenTwoVertices(int startvertex, int d) {
        return new BFS(graph).shortestDistanceLengthBetweenTwoVertices(startvertex, d);
    }

    /**
     * Refer {@link Graph#Dijkastras(int)}
     * @param startVertex
     * @return
     */
    public List<Integer> shortestDistanceLengthFromAVertexToAll(int startVertex) {
        return new BFS(graph).shortestDistanceLengthFromAVertexToAll(startVertex);
    }

    /**
     * Refer {@link Graph#Dijkastras(int)}
     * @param startVertex
     * @return
     */
    public int[] leastCostPathFromAVertexToAll(int startVertex) {
        return new BFS(graph).leastCostPathFromAVertexToAll(startVertex);
    }

    public void topSort() {
        new TopologicalSort(graph).topSort();
    }

    public Map<Integer, Integer> Dijkastras(int startVertex) {
        return new Dijkastras(graph).Dijkastras(startVertex);
    }

    public void DijkastrasWithMinPathsInitialization(int startVertex) {
        new Dijkastras(graph).DijkastrasWithMinPathsInitialization(startVertex);
    }

    // Minimum Spanning Tree
    public void Krusukal(Map<Integer, List<Edge>> graph, int startVertex) {
        //new Krusukal(graph).KURUSKAL(startVertex);
    }

    public void FLOYDWARSHALL(Map<Integer, List<Edge>> graph, int startVertex) {
        new FloydWarshall(GraphUtils.mapTo2DArray(graph)).FLOYDWARSHALL(startVertex);
    }

}
