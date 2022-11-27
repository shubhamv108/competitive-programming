/*
package code.beingzero.graphs.spoj;

import code.beingzero.graphs.Edge;
import code.beingzero.graphs.Graph;

import java.util.Map;

import static input.InputUtils.nextInt;
import static input.InputUtils.splitNextLine;
import static input.InputUtils.toInteger;

public class TRVCOST {

    public static void main(String[] args) {

        Graph grpah = new Graph();

        int N = nextInt();
        int n = N;
        while ( n-- > 0 ) {
            String[] line = splitNextLine();
            int A = toInteger(line[0]);
            int B = toInteger(line[1]);
            int W = toInteger(line[2]);
            grpah.insert(A, new Edge(B, W), true);
        }

        int U = nextInt();
        Map<Integer, Integer> cost = grpah.Dijkastras(U);
        int Q = nextInt();
        while ( Q-- > 0 ) {
            int V = nextInt();
            System.out.println(null == cost.get(V) ? "NO PATH" : cost.get(V));
        }
    }

}
*/

package code.shubham.graphs.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static input.InputUtils.nextInt;
import static input.InputUtils.splitNextLine;
import static input.InputUtils.toInteger;
import static java.lang.Integer.valueOf;
import static java.lang.System.in;

class TRVCOST {

    public static void main(String[] args) {

        Graph1 grpah = new Graph1();

        int N = nextInt();
        int n = N;
        while ( n-- > 0 ) {
            String[] line = splitNextLine();
            int A = toInteger(line[0]);
            int B = toInteger(line[1]);
            int W = toInteger(line[2]);
            grpah.insert(A, new Edge(B, W), true);
        }

        int U = nextInt();
        Map<Integer, Integer> cost = grpah.Dijkastras(U);
        int Q = nextInt();
        while ( Q-- > 0 ) {
            int V = nextInt();
            System.out.println(null == cost.get(V) ? "NO PATH" : cost.get(V));
        }
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

class Graph1 {

    private Map<Integer, List<Edge>> graph = new HashMap<>();

    private boolean[] visited;

    public void insert(Integer v, Edge e, boolean doReverseMapping) {
        if(graph.containsKey(v)) { graph.get(v).add(e); }
        else {
            ArrayList<Edge> l = new ArrayList<>();
            l.add(e);
            graph.put(v, l);
        }
        if(doReverseMapping) insert(e.v, new Edge(v, e.cost), !doReverseMapping);
    }

    public Map<Integer, Integer> Dijkastras(int startVertex) {
        return new Dijkastras(graph).Dijkastras(startVertex);
    }
}

class Edge implements Comparable<code.shubham.graphs.Edge> {

    public int v;
    public int cost;
    public Edge(int v, int w){
        this.v = v;
        this.cost = w;
    }

    public int compareTo(code.shubham.graphs.Edge o){
        return this.cost - o.cost;
    }

    @Override
    public String toString() { return "" + v + "(" + cost + ") "; }
}

class Dijkastras {

    private Map<Integer, List<Edge>> graph;

    private Map<Integer, List<Integer>> minPathCosts;

    public Dijkastras(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    // Optimal way to find SHORTEST Distance froma vertex to all
    public Map<Integer, Integer> Dijkastras(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<Integer, Integer> set  = new HashMap<>();
        pq.offer(new Edge(s, 0));
        while(!pq.isEmpty() || set.size() != graph.size()) {
            Edge e = pq.poll();
            if(!set.containsKey(e.v)) {
                set.put(e.v, e.cost);
                graph.get(e.v).forEach(edge -> {
                    if (!set.containsKey(edge.v)) {
                        pq.offer(new Edge(edge.v, e.cost + edge.cost)); }
                });
            }
        }
//        System.out.println(set);
        return set;
    }
}