package code.shubham.graphs.dijkastras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] flight: flights) {
                List<int[]> edges = graph.get(flight[0]);
                if (edges == null)
                    graph.put(flight[0], edges = new ArrayList<>());
                edges.add(new int[] { flight[1], flight[2] });
            }

            Integer[] visited = new Integer[n];
            PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
            pq.offer(new int[] { src, 0, 0 });

            while (!pq.isEmpty()) {
                int[] p = pq.poll();
                if (p[0] == dst)
                    return p[1];

                if (p[2] == k + 1)
                    continue;

                if (visited[p[0]] != null && visited[p[0]] <= p[2])
                    continue;

                visited[p[0]] = p[2];
                List<int[]> edges = graph.get(p[0]);
                if (edges == null)
                    continue;
                for (int[] edge: edges)
                    pq.offer(new int[] { edge[0], p[1] + edge[1], p[2] + 1 });
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        Solution solution = cheapestFlightsWithinKStops.new Solution();

        System.out.println(solution.findCheapestPrice(
                4,
                new int[][] {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}},
                0, 3, 1));
    }
}
