package code.shubham.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    class Solution {
        public int networkDelayTime(int[][] A, int n, int k) {
            int result = 0;

            HashMap<Integer, List<int[]>> g = new HashMap<>();
            for (int[] a : A)
                g.computeIfAbsent(a[0], e -> new ArrayList<>()).add(a);

            PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> x[2] - y[2]);
            q.offer(new int[] {-1, k, 0});

            boolean[] v = new boolean[n+1];
            int[] d = new int[n+1];
            Arrays.fill(d, Integer.MAX_VALUE);

            while (!q.isEmpty()) {
                int[] p = q.poll();
                if (v[p[1]])
                    continue;

                v[p[1]] = true;
                if (p[2] > d[p[1]]) {
                    continue;
                }
                result = p[2];

                n--;
                if (n == 0)
                    return result;

                List<int[]> L = g.get(p[1]);
                if (L == null)
                    continue;
                for (int[] l : L) {
                    if (d[l[1]] > l[2] + p[2]) {
                        l[2] += p[2];
                        d[l[1]] = l[2];
                        q.offer(l);
                    }
                }
            }

            // for (Integer w : d) {
            //     if (w == Integer.MAX_VALUE)
            //         return -1;
            //     result = Math.min(result, w);
            // }
            return n != 0 ? -1 : result;
        }
    }
}
