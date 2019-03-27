package code.graphs;

import java.util.*;
import java.util.stream.IntStream;

public class CompleteAllCourses {

    class Solution {

        void insertEdge (Map<Integer, HashSet<Integer>> m, int u, int v, boolean isBid) {
            if (m.containsKey(u)) {
                m.get(u).add(v);
            } else {
                HashSet<Integer> s = new HashSet<>();
                s.add(v);
                m.put(u, s);
            }
            if (isBid) insertEdge(m, v, u, !isBid);
            else if (!m.containsKey(u)) m.put(u, null);
        }

        private int complete (int a, Map<Integer, HashSet<Integer>> m, HashSet<Integer> v, HashSet<Integer> c) {
            if (c.contains(a)) return 1;
            if (v.contains(a) && !c.contains(a)) return 0;
            v.add(a);
            if (m.get(a) != null) for (int e : m.get(a)) if (0 == complete(e, m, v, c)) return 0;
            c.add(a);
            return 1;
        }

        public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
            Map<Integer, HashSet<Integer>> m = new HashMap<>();
            IntStream.range(0, B.size()).forEach(i -> insertEdge(m, B.get(i), C.get(i), false));
            HashSet<Integer> v = new HashSet<>();
            HashSet<Integer> c = new HashSet<>();
            for (int i=0;i<A;i++) if (complete (i, m, v, c) == 0) return 0;
            return 1;
        }

    }

}
