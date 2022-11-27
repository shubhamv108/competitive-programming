package code.shubham.contestpractice.hackerearth.sdsad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static code.shubham.contestpractice.hackerearth.sdsad.InputUtils.splitNextLine;
import static java.lang.Integer.valueOf;
import static java.lang.System.in;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toList;


class One {

    public static void main(String[] args) {
        Graph graph = new Graph();
        String[] line = InputUtils.splitNextLine();
        int N = InputUtils.toInteger(line[0]);
        int M = InputUtils.toInteger(line[1]);
        int K = InputUtils.toInteger(line[2]);

        int U, V, C;

        int m = M;
        while(m-- > 0) {
            line = InputUtils.splitNextLine();
            U = InputUtils.toInteger(line[0]);
            V = InputUtils.toInteger(line[1]);
            C = InputUtils.toInteger(line[2]);
            graph.insert(U, new Edge(V, C), true);
        }

        Integer[] cost = new Integer[N + 1];
        fill(cost, 0);

        graph.DijkastrasWithMinPathsInitialization(1);

        //Arrays.stream(ARR).forEach(e -> System.out.println(e + " "));

        for (Entry<Integer, List<Integer>> e : graph.getMinPathsCost().entrySet()) {
            List<Integer> collect = e.getValue().stream().sorted().collect(toList());
            cost[e.getKey()] = IntStream.range(0, collect.size() - K).map(i -> collect.get(i)).sum();
        }

        for (int i = 1; i < N+1; i++) {
            System.out.print(cost[i] + " ");
        }
        // Arrays.stream(cost).forEach(c -> System.out.print(c + " "));
    }

}

class Graph {

    private Map<Integer, List<Edge>> graph = new HashMap<>();

    private Map<Integer, List<Integer>> minPathCosts = new HashMap<>();

    public void insert(Integer v, Edge edge, boolean doReverseMapping) {
        if(graph.containsKey(v)) { graph.get(v).add(edge); }
        else {
            ArrayList<Edge> l = new ArrayList<>();
            l.add(edge);
            graph.put(v, l);
        }
        if(doReverseMapping) insert(edge.v, new Edge(v, edge.c), !doReverseMapping);
    }

    /**public int[] leastCostPathFromAVertexToAll(int s) {
        if(null == graph || graph.isEmpty() || !graph.containsKey(s)) return null;
        boolean[] visited = new boolean[graph.size() + 1];
        int[] cost = new int[graph.size() + 1];
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
                        if(cost[a] + edge.c < cost[edge.v]) {
                            List<Integer> costArrayOfCurrentNode = new ArrayList<>();
                            if (null != minPathCosts.get(a)) {
                                costArrayOfCurrentNode.addAll(minPathCosts.get(a));
                            }
                            costArrayOfCurrentNode.add(edge.c);
                            minPathCosts.put(edge.v, costArrayOfCurrentNode);
                            cost[edge.v] = cost[a] + edge.c;
                            q.offer(edge.v);
                        }
                    }
            });
        }
        return cost;
    }*/

    public void DijkastrasWithMinPathsInitialization(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() != graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.c);
                graph.get(e.v).forEach(edge -> {
                    if (!set.containsKey(edge.v)) {
                        List<Integer> costArrayOfCurrentNode = new ArrayList<>();
                        if (null != minPathCosts.get(e.v)) {
                            costArrayOfCurrentNode.addAll(minPathCosts.get(e.v));
                        }
                        costArrayOfCurrentNode.add(edge.c);
                        minPathCosts.put(edge.v, costArrayOfCurrentNode);
                        pq.offer(new Edge(edge.v, e.c + edge.c));
                    }
                });
            }
        }
        System.out.println(set);
    }

    public Map<Integer, List<Integer>> getMinPathsCost() {
        return minPathCosts;
    }

}

class Edge implements Comparable<Edge> {
    public int v;
    public int c;
    public Edge(int v, int c){
        this.v = v;
        this.c = c;
    }

    @Override
    public String toString() { return "" + v + "(" + c + ") "; }

    @Override
    public int compareTo(Edge o) {
        return this.c - o.c;
    }
}

class InputUtils {

    private static BufferedReader BR;

    public static BufferedReader getBR() {
        if (null == BR) {
            BR = new BufferedReader(new InputStreamReader(in));
        }
        return BR;
    }

    public static String[] splitNextLine() {
        return splitNextLine(BR, " ");
    }

    public static String[] splitNextLine(BufferedReader br) {
       return splitNextLine(br, " ");
    }

    public static String[] splitNextLine(BufferedReader br, String regex) {
        return nextLine().split(regex);
    }

    public static String nextLine() {
        return nextLine(getBR());
    }

    public static String nextLine(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static int toInteger(String s) {
        return valueOf(s);
    }

    public static long toLong(String s) {
        return Long.valueOf(s);
    }

    public static int nextInt() {
        return toInteger(nextLine());
    }

    public static long nextLong() {
        return toLong(nextLine());
    }
}
