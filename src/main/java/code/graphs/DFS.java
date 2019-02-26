package code.graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DFS {

    private Map<Integer, List<Edge>> graph;

    private boolean[] visited;

    public DFS(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    private void DFSUtil(int v) {
        visited[v] = true;
        graph.get(v).forEach(edge -> { if (!visited[edge.v]) DFSUtil(edge.v); });
    }

    public void DFS() {
        visited = new boolean[graph.size()];
        for (int i=0; i<graph.size(); ++i) if (visited[i] == false) DFSUtil(i);
    }

    private boolean printDFSPathUtil(int v, int d, int[] path, int pi) {
        visited[v] = true;
        if(v == d) return true;
        path[pi] = v;
        for(Edge e : graph.get(v)) {
            if (!visited[e.v] && printDFSPathUtil(e.v, d, path, pi + 1)) return true;
        }
        return false;
    }

    public void printDFSPath(int s, int d){
        if(graph == null || graph.isEmpty() || !graph.containsKey(s) || !graph.containsKey(d)) return;
        visited = new boolean[graph.size() + 1];
        int[] path = new int[graph.size()];
        Arrays.fill(path, -1);
        printDFSPathUtil(s, d, path, 0);
        if (path[0] == -1) System.out.printf("%d->", s);
        else Arrays.stream(path).filter(p -> p != -1).forEach(p -> System.out.printf("%d->", p));
        System.out.printf("%d", d);
    }

    private void visit(int v) { visited[v] = true; }

    private void visitAndPrint(int v) {
        visit(v);
        System.out.printf("%d ", v);
    }

    private void visitNeighbourVertices(int v) {
        graph.get(v).forEach(edge -> {
            if(!visited[edge.v]) visitVertex(edge.v);
        });
    }

    private void visitNeighbourVerticesAndPrint(int v) {
        graph.get(v).forEach(edge -> {
            if(!visited[edge.v]) visitVertexAndPrint(edge.v);
        });
    }

    private void visitVertex(int v) {
        visit(v);
        visitNeighbourVertices(v);
    }

    private void visitVertexAndPrint(int v) {
        visitAndPrint(v);
        visitNeighbourVerticesAndPrint(v);
    }

    public void dfs(){
        if(graph == null || graph.isEmpty()) return;
        for(Map.Entry vertex : graph.entrySet()) {
            int v = (int) vertex.getKey();
            if(!visited[v]) visitVertex(v);
        }
    }

    public int connectedComponentsCount(){
        int connectedComponents = 0;
        if(graph == null || graph.isEmpty()) return 0;
        visited = new boolean[graph.size() + 1];
        for(Map.Entry vertex : graph.entrySet()) {
            int v = (int) vertex.getKey();
            if(!visited[v]) {
                connectedComponents++;
                visitVertex(v);
            }
        }
        return connectedComponents;
    }

}
