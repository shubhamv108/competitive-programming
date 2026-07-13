package code.shubham.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class NearestServersByCapacity {

    public class Solution {

        public List<Integer> getMinCost(List<Integer> C, List<Integer> F, List<Integer> T) {
            int n = C.size();
            int m = F.size();

            // Sort servers by capacity to find closest neighbors
            Integer[] order = new Integer[n];
            for (int i = 0; i < n; ++i) order[i] = i;
            Arrays.sort(order, (x, y) -> C.get(x) - C.get(y));

            int[] pos = new int[n];
            for (int i = 0; i < n; ++i) pos[order[i]] = i;

            int[] closest = new int[n];
            for (int i = 0; i < n; ++i) {
                int p = pos[i];
                if (p == 0) {
                    closest[i] = order[1];
                } else if (p == n - 1) {
                    closest[i] = order[n - 2];
                } else {
                    int leftDiff  = C.get(i) - C.get(order[p - 1]);
                    int rightDiff = C.get(order[p + 1]) - C.get(i);
                    closest[i] = leftDiff <= rightDiff ? order[p - 1] : order[p + 1];
                }
            }

            // Build adjacency list
            // Edges:
            // 1) Between consecutive sorted neighbors: cost = |cap[u] - cap[v]|
            // 2) Each node to its closest: cost = 1 (bidirectional)
            List<long[]>[] g = new ArrayList[n];
            for (int i = 0; i < n; ++i) g[i] = new ArrayList<>();

            for (int i = 0; i < n - 1; ++i) {
                int u = order[i], v = order[i + 1];
                long w = C.get(v) - C.get(u); // always positive (sorted)
                g[u].add(new long[]{v, w});
                g[v].add(new long[]{u, w});
            }

            for (int i = 0; i < n; ++i) {
                int cl = closest[i];
                g[i].add(new long[]{cl, 1});
                g[cl].add(new long[]{i, 1});
            }

            // Group queries by source to reuse Dijkstra results
            HashMap<Integer, List<Integer>> queryBySource = new HashMap<>();
            for (int i = 0; i < m; ++i) {
                queryBySource.computeIfAbsent(F.get(i), k -> new ArrayList<>()).add(i);
            }

            int[] result = new int[m];

            for (int src : queryBySource.keySet()) {
                long[] dist = dijkstra(src, g, n);
                for (int idx : queryBySource.get(src)) {
                    result[idx] = (int) dist[T.get(idx)];
                }
            }

            List<Integer> ans = new ArrayList<>();
            for (int r : result) ans.add(r);
            return ans;
        }

        private long[] dijkstra(int src, List<long[]>[] g, int n) {
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[src] = 0;

            // PQ: [dist, node]
            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
            pq.add(new long[]{0, src});

            while (!pq.isEmpty()) {
                long[] cur = pq.poll();
                long d = cur[0];
                int u = (int) cur[1];

                if (d > dist[u]) continue;

                for (long[] edge : g[u]) {
                    int v = (int) edge[0];
                    long nd = d + edge[1];
                    if (nd < dist[v]) {
                        dist[v] = nd;
                        pq.add(new long[]{nd, v});
                    }
                }
            }

            return dist;
        }
    }

    public class Solution2 {

        public static List<Integer> getMinCost(List<Integer> C, List<Integer> F, List<Integer> T) {
            int n = C.size();

            // Step 1: sort indices
            Integer[] order = IntStream.range(0, n)
                    .boxed()
                    .toArray(Integer[]::new);
            Arrays.sort(order, Comparator.comparingInt(C::get));

            int[] pos = new int[n];
            IntStream.range(0, n).forEach(i -> pos[order[i]] = i);

            // Step 2: prefix distances
            long[] pref = new long[n];
            for (int i = 1; i < n; ++i)
                pref[i] = pref[i - 1] + (C.get(order[i]) - C.get(order[i - 1]));

            // Step 3: closest pointer
            int[] next = new int[n];
            for (int i = 0; i < n; ++i) {
                int p = pos[i];

                int left = (p > 0) ? order[p - 1] : -1;
                int right = (p < n - 1) ? order[p + 1] : -1;

                if (left == -1) next[i] = right;
                else if (right == -1) next[i] = left;
                else {
                    int d1 = Math.abs(C.get(i) - C.get(left));
                    int d2 = Math.abs(C.get(i) - C.get(right));
                    next[i] = (d1 <= d2) ? left : right;
                }
            }

            // Step 4: DP - best cost using closest edges
            long[] best = new long[n];
            Arrays.fill(best, Long.MAX_VALUE);

            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; ++i) {
                if (visited[i]) continue;

                // detect cycle
                Map<Integer, Integer> map = new HashMap<>();
                int cur = i;
                int step = 0;

                while (!map.containsKey(cur) && !visited[cur]) {
                    map.put(cur, step++);
                    cur = next[cur];
                }

                // process cycle
                if (!visited[cur]) {
                    int cycleStart = cur;

                    List<Integer> cycle = new ArrayList<>();
                    do {
                        cycle.add(cur);
                        cur = next[cur];
                    } while (cur != cycleStart);

                    // initialize cycle best
                    for (int node : cycle) {
                        best[node] = 0;
                    }
                }

                // backtrack
                cur = i;
                while (!visited[cur]) {
                    visited[cur] = true;
                    int nxt = next[cur];
                    best[cur] = Math.min(best[cur], best[nxt] + 1);
                    cur = nxt;
                }
            }

            // helper: distance using sorted array
            java.util.function.BiFunction<Integer, Integer, Long> dist =
                    (u, v) -> Math.abs(pref[pos[u]] - pref[pos[v]]);

            // Step 5: answer queries in O(1)
            List<Integer> ans = new ArrayList<>();

            for (int i = 0; i < F.size(); ++i) {
                int u = F.get(i);
                int v = T.get(i);

                long res = dist.apply(u, v);

                // try using closest chain
                long candidate = best[u] + dist.apply(next[u], v);

                res = Math.min(res, candidate);

                ans.add((int) res);
            }

            return ans;
        }
    }

    void main(String[] args) {
        // Sample case 0: capacity=[2,3,5,6], from=[0,2,0], to=[3,0,1]
        System.out.println(new NearestServersByCapacity().new Solution().getMinCost(
                Arrays.asList(2, 3, 5, 6),
                Arrays.asList(0, 2, 0),
                Arrays.asList(3, 0, 1)
                                     )); // Expected: [4, 3, 1]

        // Problem example: capacity=[2,7,10], from=[0,1,2], to=[2,2,1]
        System.out.println(new NearestServersByCapacity().new Solution().getMinCost(
                Arrays.asList(2, 7, 10),
                Arrays.asList(0, 1, 2),
                Arrays.asList(2, 2, 1)
                                     )); // Expected: [2, 1, 1]
    }
}
