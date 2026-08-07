package code.shubham.graphs.tarjans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionsInANetwork {
    class Solution {
        int timer = 0;
        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> A) {
            ArrayList<Integer>[] g = new ArrayList[n];
            for (int i = 0; i < n; ++i)
                g[i] = new ArrayList<>();

            for (List<Integer> a : A) {
                g[a.get(0)].add(a.get(1));
                g[a.get(1)].add(a.get(0));
            }

            ArrayList<List<Integer>> result = new ArrayList<>();
            recurse(g, 0, -1, new int[1], new int[n], new boolean[n], result);
            return result;
        }


        public void recurse(ArrayList<Integer>[] g, int n, int p, int[] timer, int[] lowestTimestamp, boolean[] visited, List<List<Integer>> result) {
            visited[n] = true;
            lowestTimestamp[n] = this.timer++;
            int currentTimeStamp = lowestTimestamp[n];

            for (int next : g[n]) {
                if (next == p)
                    continue;

                if (!visited[next])
                    recurse(g, next, n, timer, lowestTimestamp, visited, result);

                lowestTimestamp[n] = Math.min(lowestTimestamp[n], lowestTimestamp[next]);

                if (currentTimeStamp < lowestTimestamp[next])
                    result.add(Arrays.asList(n, next));
            }
        }
    }
}
