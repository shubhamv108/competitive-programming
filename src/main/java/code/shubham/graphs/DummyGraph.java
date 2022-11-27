package code.shubham.graphs;

import java.util.*;

import static java.util.Arrays.fill;

public class DummyGraph<V, E> {
    private Map<V, List<E>> graph = new HashMap<>();
    private int V = graph.size();

    private Map<Integer, List<Integer>> minPathCosts = new HashMap<>();

    public Map<V, List<E>> getGraphMap() { return graph; }
//    public void setGraphMap(Map<V1, List<V2>> graph) { this.graph = graph; }
    public void setGraphMap(HashMap<V, List<E>> graph) { this.graph =  graph; }

    public void insert(V v, E e, boolean doReverseMapping) {
        if(graph.containsKey(v)) { graph.get(v).add(e); V++; }
        else {
            ArrayList<E> l = new ArrayList<>();
            l.add(e);
            graph.put(v, l);
        }
        if(doReverseMapping && e.getClass().equals(v.getClass())) insert((V)e, (E)v, !doReverseMapping);
    }

    public void print() {
        for (Map.Entry e : graph.entrySet()) {
            System.out.printf("%d : ", e.getKey());
            List<E> l = (List<E>) e.getValue();
            l.stream().forEach(n -> System.out.printf("%s", n.toString()));
            System.out.printf("\n");
        }
        System.out.printf("\n");

    }

    /**
     * DFS of undirected graph
     *
     * 6 8
     * 0 2
     * 1 2
     * 2 3
     * 2 4
     * 5 7
     * 5 6
     */
    private boolean[] visited;

    private void visit(int v) { visited[v] = true; }

    private void visitAndPrint(int v) {
        visit(v);
        System.out.printf("%d ", v);
    }

    private void visitNeighbourVertices(Map<Integer, List<Integer> > g, int v) {
        g.get(v).forEach(n -> {
            if(!visited[n]) visitVertex(g, n);
        });
    }

    private void visitNeighbourVerticesAndPrint(Map<Integer, List<Integer> > g, int v) {
        g.get(v).forEach(n -> {
            if(!visited[n]) visitVertexAndPrint(g, n);
        });
    }

    private void visitVertex(Map<Integer, List<Integer> > g, int v) {
        visit(v);
        visitNeighbourVertices(g, v);
    }

    private void visitVertexAndPrint(Map<Integer, List<Integer> > g, int v) {
        visitAndPrint(v);
        visitNeighbourVerticesAndPrint(g, v);
    }

    public void dfs(Map<Integer, List<Integer>> g){
        if(g == null || g.isEmpty()) return;
        for(Map.Entry vertex : g.entrySet()) {
            int v = (int) vertex.getKey();
            if(!visited[v]) visitVertex(g, v);
        }
    }

    private void visitNeighbourVertices(HashMap<Integer, ArrayList<Integer> > g, int v) {
        g.get(v).forEach(n -> {
            if(!visited[n]) visitVertex(g, n);
        });
    }

    private void visitVertex(HashMap<Integer, ArrayList<Integer> > g, int v) {
        visit(v);
        visitNeighbourVertices(g, v);
    }

    public void dfs(HashMap<Integer, ArrayList<Integer> > g){
        if(g == null || g.isEmpty()) return;
        for(Map.Entry vertex : g.entrySet()) {
            int v = (int) vertex.getKey();
            if(!visited[v]) visitVertex(g, v);
        }
    }


    public void BFS(HashMap<Integer, ArrayList<Integer> > g, int s) {
        boolean[] visited = new boolean[g.size()];
        Queue<Integer> q = new LinkedList<>();
        visited[s] = q.offer(s);
        while(!q.isEmpty()) {
            s = q.poll();
            System.out.printf("%d ", s);
            g.get(s).forEach(v -> { if(!visited[v]) visited[v] = q.offer(v); });
        }
    }

    public int connectedComponentsCount(HashMap<Integer, ArrayList<Integer> > g){
        int connectedComponents = 0;
        if(g == null || g.isEmpty()) return 0;
        for(Map.Entry vertex : g.entrySet()) {
            int v = (int) vertex.getKey();
            if(!visited[v]) {
                connectedComponents++;
                visitVertex(g, v);
            }
        }
        return connectedComponents;
    }

    private void DFSUtil(int v) {
        visited[(int) v] = true;
        ((ArrayList<Integer>) graph.get(v)).forEach(n -> { if (!visited[n]) DFSUtil(n); });
    }

    public void DFS() {
        visited = new boolean[graph.size()];
        for (int i=0; i<graph.size(); ++i) if (visited[i] == false) DFSUtil(i);
    }

    private boolean printDFSPathUtil(int v, int d, int[] path, int pi) {
        visited[v] = true;
        if(v == d) return true;
        path[pi] = v;
        for(int n : (ArrayList<Integer>) graph.get(v)) {
            if (!visited[n] && printDFSPathUtil(n, d, path, pi + 1)) return true;
        }
        return false;
    }

    public void printDFSPath(HashMap<Integer, ArrayList<Integer> > g, int s, int d){
        if(g == null || g.isEmpty() || !g.containsKey(s) || !g.containsKey(d)) return;
        visited = new boolean[graph.size()];
        int[] path = new int[graph.size()];
        Arrays.fill(path, -1);
        printDFSPathUtil(s, d, path, 0);
        if (path[0] == -1) System.out.printf("%d->", s);
        else Arrays.stream(path).filter(p -> p != -1).forEach(p -> System.out.printf("%d->", p));
        System.out.printf("%d", d);
    }

    public int shortestDistanceLengthBetweenTwoVertices(HashMap<Integer, ArrayList<Integer> > g, int s, int d) {
        if(g == null || g.isEmpty() || !g.containsKey(s) || !g.containsKey(d)) return 0;
        if(s == d) return 0;
        boolean[] visited = new boolean[g.size() + 1];
        int[] level = new int[g.size()];
        level[d] = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        level[s] = 0;
        while(!q.isEmpty()) {
            int a = q.poll();
            if(a == d) break;
            visited[a] = true;
            g.get(a).forEach(n -> { if (!visited[n]) { level[n] = level[a] + 1; q.offer(n); } });
        }
        return level[d];
    }

    /**
     * Refer {@link Graph#Dijkastras(Map, int)}}
     * @param g
     * @param s
     * @return
     */
    public ArrayList<Integer> shortestDistanceLengthFromAVertexToAll(HashMap<Integer, ArrayList<Integer> > g, int s) {
        if(g == null || g.isEmpty() || !g.containsKey(s)) return null;
        boolean[] visited = new boolean[g.size()];
        Integer[] level = new Integer[g.size()];
        Arrays.fill(level, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        level[s] = 0;
        while(!q.isEmpty()) {
            int a = q.poll();
            visited[a] = true;
            g.get(a).forEach(n -> { if (!visited[n]) { if(level[a] + 1 < level[n]) level[n] = level[a] + 1; q.offer(n); } });
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
                        /*if (!visited[edge.v]) {
                            if(cost[a] + edge.c < cost[edge.v]) {
                                cost[edge.v] = cost[a] + edge.c;
                                q.offer(edge.v);
                            }
                        }*/
                    });
        }
        return cost;
    }

    private int[] inDegree = new int[graph.size()];

//    private void updateInDegree() { for (V e : graph.keySet()) for (E e1 : graph.get(e)) inDegree[(int) e1]++; }

    void topSort(Map<Integer, List<Integer> > g) {
        visited = new boolean[g.size()];
        int countOfVisited = 0;
//        updateInDegree();
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<V;i++) { if (inDegree[i] == 0)  q.offer(i); }
        while(!q.isEmpty()) {
            int a = q.poll();
            visited[a] = true;
            countOfVisited++;
            System.out.printf("%d ", a);
//            for (E v : graph.get(a)) if (!visited[(int) v] && --inDegree[(int) v] == 0) q.offer((int) v);
        }
        if(countOfVisited != V) { System.out.println("Topological Sort not possible::There exists a cycle"); return; }
    }

    // Optimal way to find SHORTEST Distance froma vertex to all
    public void Dijkastras(Map<Integer, List<Edge>> graph, int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        set.put(s, 0);
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() == graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.cost);
                graph.get(e.v).forEach(edge -> {
                    if (!set.containsKey(edge.v)) pq.offer(new Edge(edge.v, e.cost + edge.cost));
                });
            }
        }
        System.out.println(set);
    }

    public void DijkastrasWithMinPathsInitialization(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() != graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.cost);
                /*graph.get(e.v).forEach((Edge) edge -> {
                    if (!set.containsKey(edge.v)) {
                        List<Integer> costArrayOfCurrentNode = new ArrayList<>();
                        if (null != minPathCosts.get(e.v)) {
                            costArrayOfCurrentNode.addAll(minPathCosts.get(e.v));
                        }
                        costArrayOfCurrentNode.add(edge.cost);
                        minPathCosts.put(edge.v, costArrayOfCurrentNode);
                        pq.offer(new Edge(edge.v, e.cost + edge.cost));
                    }
                });*/
            }
        }
        System.out.println(set);
    }

    public void Krusukal(Map<Integer, List<Edge>> graph, int s) {

    }


    /**
     * unwieghted graph eg
     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Graph graph = new Graph<Integer, Object>();
//        int V = Integer.parseInt(sc.next());
//        int E = Integer.parseInt(sc.next());
//        graph.visited = new boolean[V];
//        while (E-- > 0) {
//            int k = Integer.parseInt(sc.next());
//            int v = Integer.parseInt(sc.next());
//            graph.insert(k, v,true);
//        }
//        graph.print();
//        graph.shortestDistanceLength(graph.graph, 0).forEach(System.out::print);
//    }
//8 7
//0 2
//2 3
//5 7
//2 4
//5 6
//1 2
//1 5

    public static void insert( Map<Integer, ArrayList<Edge>> graph, int v, Edge e) {
//        if(graph.containsKey(v)) { graph.get(v).add(e); V++; }
//        else {
//            ArrayList<Edge> l = new ArrayList<>();
//            l.add(e);
//            graph.put(v, l);
//        }
    }

    /**
     * wieghted grpah example
     * @param args
     */
    public static void main(String[] args) {
        Map<Integer, ArrayList<Edge>> graph = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int V = Integer.parseInt(sc.next());
        int E = Integer.parseInt(sc.next());
        while (E-- > 0) {
            int u = Integer.parseInt(sc.next());
            int v = Integer.parseInt(sc.next());
            int w = Integer.parseInt(sc.next());
            insert(graph, u, new Edge(v, w));
            insert(graph, v, new Edge(u, w));
        }
        int s = sc.nextInt();
        
        //graph.Dijkastras(graph, s);
    }

}
