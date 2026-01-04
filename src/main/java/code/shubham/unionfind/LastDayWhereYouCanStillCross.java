package code.shubham.unionfind;

import java.util.BitSet;

public class LastDayWhereYouCanStillCross {
    class Solution {
        int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        public int latestDayToCross(int row, int col, int[][] A) {
            int rc = row * col;
            UF uf = new UF(rc + 2);
            BitSet w = new BitSet(rc);


            for (int i = 0; i < A.length; ++i) {
                int R = A[i][0] - 1, C = A[i][1] - 1;
                int index1 = R * col + C + 1;
                w.set(index1);
                for (int[] d : dirs) {
                    int r = R + d[0], c = C + d[1];
                    int index2 = r * col + c + 1;
                    if (r < 0 || c < 0 || r >= row || c >= col || !w.get(index2))
                        continue;

                    uf.union(index1, index2);
                }

                if (C == 0)
                    uf.union(0, index1);
                if (C == col - 1)
                    uf.union(rc + 1, index1);
                if (uf.find(0) == uf.find(rc + 1))
                    return i;
            }
            return -1;
        }

        class UF {
            int[] p, size;
            UF (int n) {
                p = new int[n];
                size = new int[n];
                for (int i = 0; i < n; ++i) {
                    p[i] = i;
                    size[i] = 1;
                }
            }

            int find (int x) {
                if (p[x] != x)
                    p[x] = find(p[x]);
                return p[x];
            }

            void union (int x, int y) {
                x = find(x);
                y = find(y);
                if (x == y)
                    return;

                if (size[x] > size[y]) {
                    int t = size[x];
                    size[x] = size[y];
                    size[y] = t;
                }

                p[x] = y;
                size[y] += size[x];
            }
        }
    }

    public static void main(String[] args) {

    }
}
