package code.shubham.unionfind;

public class RedundantConnectionII {
    class Solution {
        public int[] findRedundantDirectedConnection(int[][] A) {
            int[] p = new int[A.length + 1],x  = null, y = null;
            for (int[] a : A) {
                if (p[a[1]] == 0)
                    p[a[1]] = a[0];
                else {
                    x = new int[] { p[a[1]], a[1] };
                    y = new int[] { a[0], a[1] };
                    a[1] = 0;
                }
            }

            UF uf = new UF(A.length + 1);
            for (int[] a : A)
                if (!uf.union(a[0], a[1])) {
                    if (x != null)
                        return x;
                    return a;
                }
            return y;
        }

        class UF {
            int[] p, size, rank;
            UF (int n) {
                p = new int[n];
                size = new int[n];
                for (int i = 0; i < n; ++i) {
                    size[i] = 1;
                    p[i] = i;
                }
            }

            int find(int x) {
                if (x != p[x])
                    p[x] = find(p[x]);
                return p[x];
            }

            boolean union(int x, int y) {
                x = find(x);
                y = find(y);
                if (x == y)
                    return false;

                if (size[x] > size[y]) {
                    int t = x;
                    x = y;
                    y = t;
                }

                p[x] = y;
                size[y] += size[x];
                return true;
            }
        }
    }
}
