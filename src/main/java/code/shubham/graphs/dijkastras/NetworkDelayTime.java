package code.shubham.graphs.dijkastras;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    class Solution {
        public int networkDelayTime(int[][] A, int n, int k) {
            ArrayList<int[]>[] g = new ArrayList[n+1];
            for (int i = 0; i <= n; ++i)
                g[i] = new ArrayList<>();
            for (int[] a : A)
                g[a[0]].add(a);

            int[] v = new int[n+1];
            v[k] = 0;
            PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> v[x] - v[y]);
            q.offer(k);
            int visited = 0;
            while (!q.isEmpty()) {
                int p = q.poll();
                ++visited;
                if (visited == n)
                    return v[p];
                for (int[] next : g[p]) {
                    int nextDist = v[p] + next[2];
                    if (v[next[1]] > nextDist) {
                        v[next[1]] = nextDist;
                        q.offer(next[1]);
                    }
                }
            }
            return -1;
        }
    }

    void main() {
        System.out.println(new Solution()
               .networkDelayTime(new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } }, 4, 2));
    }
}
