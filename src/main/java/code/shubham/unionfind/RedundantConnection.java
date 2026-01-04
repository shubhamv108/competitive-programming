package code.shubham.unionfind;

public class RedundantConnection {
    class Solution {
        public int[] findRedundantConnection(int[][] A) {
            int[] result = new int[2];
            UF uf = new UF(A.length + 1);
            for (int[] a : A)
                if (!uf.union(a[0], a[1])) {
                    result[0] = a[0];
                    result[1] = a[1];
                }
            return result;
        }

        class UF {
            int[] p;
            UF (int n) {
                p = new int[n];
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

                p[x] = y;
                return true;
            }
        }
    }

    public static void main(String[] args) {
        int[] result = new RedundantConnection().new Solution().findRedundantConnection(new int[][] {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 4},
            {1, 5}
        });
        System.out.println(result[0] + " " + result[1]);
    }
}
