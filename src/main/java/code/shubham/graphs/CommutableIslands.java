package code.shubham.graphs;

import java.util.*;

public class CommutableIslands {

    class Edge {
        int v;
        int w;
        Edge (int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private void insertEdge (Map<Integer, ArrayList<Edge>> m, int u, int v, int w, boolean isBidirectional) {
        if (m.containsKey (u)) {
            m.get(u).add(new Edge (v, w));
        } else {
            ArrayList<Edge> l = new ArrayList<>();
            l.add(new Edge (v, w));
            m.put(u, l);
        }
        if (isBidirectional) insertEdge(m, v, u, w, !isBidirectional);
    }

    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer, ArrayList<Edge>> m = new HashMap<>();
        B.stream().forEach(e -> insertEdge(m, e.get(0), e.get(1), e.get(2), true));
        Comparator<Edge> c = (a, b) -> { if (a.w > b.w) return 1; else return -1; };
        PriorityQueue<Edge> q = new PriorityQueue<>(c);
        Set<Integer> s = new HashSet<>();
        q.addAll(m.get(1));
        s.add(1);
        int sum = 0;
        int i = 1;
        while (i < A) {
            Edge e = q.poll();
            if (!s.contains(e.v)) {
                sum += e.w;
                q.addAll(m.get(e.v));
                i++;
                s.add(e.v);
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.add(new ArrayList(Arrays.asList(1, 2, 1)));
        a.add(new ArrayList(Arrays.asList(2, 3, 2)));
        a.add(new ArrayList(Arrays.asList(3, 4, 4)));
        a.add(new ArrayList(Arrays.asList(1, 4, 3)));
        System.out.println(new CommutableIslands().solve(4, a));
    }

}
