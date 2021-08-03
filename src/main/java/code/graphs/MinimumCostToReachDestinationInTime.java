package code.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumCostToReachDestinationInTime {

    class Solution {
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
        void add(int x, int y, int t) {
            ArrayList<int[]> l = graph.get(x);
            if (l == null) {
                graph.put(x, l = new ArrayList<>());
            }
            l.add(new int[]{y, t});
        }
        public int minCost(int maxTime, int[][] edges, int[] Fees) {
            Integer[] times = new Integer[Fees.length];
            for (int[] e: edges) {
                add(e[0], e[1], e[2]);
                add(e[1], e[0], e[2]);
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
            pq.add(new int[] { 0, Fees[0], 0 });
            times[0] = 0;
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int u = curr[0];
                int cost = curr[1];
                int time = curr[2];
                if (u == Fees.length - 1) return cost;
                for (int[] next : graph.get(u)) {
                    int v = next[0];
                    if (times[v] != null && time + next[1] >= times[v]) continue;
                    times[v] = time + next[1];
                    if (time + next[1] <= maxTime){
                        pq.add(new int[] { v,cost + Fees[v], time + next[1] });
                    }
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumCostToReachDestinationInTime().new Solution()
                        .minCost(30, new int[][] {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}}, new int[] {5,1,2,20,20,3})
        );
    }

}
