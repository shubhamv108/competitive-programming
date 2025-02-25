package code.shubham.trees;

public class GraphvalidTree {
    public class Solution {
        /**
         * @param n: An integer
         * @param edges: a list of undirected edges
         * @return: true if it's a valid tree, or false
         */
        public boolean validTree(int n, int[][] A) {
            if (A.length != n-1)
                return false;

            UF uf = new UF(n);
            for (int[] a : A)
                uf.union(a[0], a[1]);
            return uf.count == 1;
        }

        class UF {
            int[] parent;
            int count;

            UF(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; ++i)
                    parent[i] = i;
            }

            int find(int a) {
                if (parent[a] != a)
                    parent[a] = find(parent[a]);
                return parent[a];
            }

            void union(int x, int y) {
                x = find(x);
                y = find(y);
                if (x != y) {
                    parent[y] = x;
                    --count;
                }
            }
        }
    }
}
