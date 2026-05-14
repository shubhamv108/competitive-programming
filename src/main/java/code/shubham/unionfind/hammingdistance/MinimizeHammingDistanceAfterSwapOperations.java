package code.shubham.unionfind.hammingdistance;

import java.util.HashMap;

public class MinimizeHammingDistanceAfterSwapOperations {

    class Solution {
        public int minimumHammingDistance(int[] S, int[] T, int[][] AS) {
            int n = S.length, result = 0;
            UF uf = new UF(n);
            for (int[] as : AS)
                uf.union(as[0], as[1]);

            HashMap<Integer, HashMap<Integer, Integer>> sets = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                int set = uf.find(i);
                HashMap<Integer, Integer> c = sets.computeIfAbsent(set, _ -> new HashMap<>());
                c.put(S[i], c.getOrDefault(S[i], 0) + 1);
            }

            for (int i = 0; i < n; ++i) {
                int set = uf.find(i);
                HashMap<Integer, Integer> c = sets.get(set);
                int f = c.getOrDefault(T[i], 0);
                if (f > 0)
                    c.put(T[i], f - 1);
                else
                    ++result;
            }

            return result;
        }
    }

    class UF {

        int[] p;

        UF (int n) {
            p = new int[n];
            for (int i = 0; i < n; ++i)
                p[i] = i;
        }

        int find(int x) {
            if (p[x] != x)
                p[x] = find(p[x]);
            return p[x];
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);

            if (x == y)
                return;

            p[y] = x;
        }
    }

}
