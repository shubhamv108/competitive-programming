package code.shubham.unionfind;

import java.util.HashMap;
import java.util.HashSet;

public class MostStonesRemovedWithSameRowOrSameColumn {
    class Solution {
        public int removeStones(int[][] A) {
            int combinedStones = 0;
            HashSet<int[]> visited = new HashSet<>();
            for (int[] a : A) {
                if (visit(a, A, visited))
                    combinedStones++;
            }
            return A.length - combinedStones;
        }

        boolean visit(int[] x, int[][] A, HashSet<int[]> visited) {
            if (visited.contains(x))
                return false;

            visited.add(x);
            for (int[] a : A)
                if (a[0] == x[0] || a[1] == x[1])
                    visit(a, A, visited);

            return true;
        }
    }

    class Solution2 {
        HashMap<Integer, Integer> f = new HashMap<>();
        int islands = 0;

        public int removeStones(int[][] stones) {
            for (int i = 0; i < stones.length; ++i)
                union(stones[i][0], ~stones[i][1]);
            return stones.length - islands;
        }

        public int find(int x) {
            if (f.putIfAbsent(x, x) == null)
                islands++;
            if (x != f.get(x))
                f.put(x, find(f.get(x)));
            return f.get(x);
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                f.put(x, y);
                islands--;
            }

        }
    }

    class Solution3 {
        int[] parent = new int[20001];
        int islands = 0;

        public int removeStones(int[][] stones) {
            for (int i = 0; i < stones.length; ++i) {
                union(stones[i][0], stones[i][1] + 10000);
            }

            return stones.length - islands;
        }

        public int find(int x) {
            if (parent[x] == 0) {
                islands++;
                parent[x] = x;
            }
            if (x != parent[x])
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                parent[x] = y;
                islands--;
            }
        }
    }
}
