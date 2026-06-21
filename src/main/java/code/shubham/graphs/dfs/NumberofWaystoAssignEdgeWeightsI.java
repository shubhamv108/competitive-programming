package code.shubham.graphs.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberofWaystoAssignEdgeWeightsI {
    class Solution {

        private static final int MOD = 1000000007;

        public int assignEdgeWeights(int[][] A) {
            int n = A.length + 1;
            List<Integer>[] g = new ArrayList[n+1];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] a : A) {
                g[a[0]].add(a[1]);
                g[a[1]].add(a[0]);
            }

            return (int) qpow(2, height(1, 0, g) - 1);
        }

        int height(int n, int parent, List<Integer>[] g) {
            int h = 0;
            for (int child : g[n]) {
                if (child == parent)
                    continue;
                h = Math.max(h, height(child, n, g) + 1);
            }
            return h;
        }

        private long qpow(int base, int exp) {
            long result = 1;
            while (exp > 0) {
                if ((exp & 1) == 1)
                    result = (result * base) % MOD;
                base = (base * base) % MOD;
                exp >>= 1;
            }
            return result;
        }
    }

    static void main() {
        System.out.println(new NumberofWaystoAssignEdgeWeightsI().new Solution().assignEdgeWeights(new int[][] {

        }));
    }
}
