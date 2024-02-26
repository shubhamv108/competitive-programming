package code.shubham.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfislandsII {
    class Solution {
        int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            ArrayList<Integer> result = new ArrayList<>();
            UF uf = new UF(m * n);

            for (int[] pos : positions) {
                int da = (pos[0] * n) + pos[1];
                uf.mark(da);
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0], y = pos[1] + dir[1];
                    if (x < 0 || y < 0 || x == m || y == n)
                        continue;
                    uf.union(da, (x * n) + y);
                }
                result.add(uf.islands);
            }
            return result;
        }

        class UF {

            int[] parent;
            int islands = 0;

            UF (int n) {
                parent = new int[n];
                Arrays.fill(parent, -1);
            }

            void mark(int x) {
                parent[x] = x;
                islands++;
            }

            int find(int x) {
                if (parent[x] != x)
                    parent[x] = find(parent[x]);
                return parent[x];
            }

            void union(int x, int y) {
                if (parent[y] == -1)
                    return;

                x = find(x);
                if (x != find(y)) {
                    parent[y] = x;
                    islands--;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new NumberOfislandsII().new Solution().numIslands2(3, 3, new int[][] {{0,0}, {0,1}, {1,2}, {2,1}})
        );
    }
}
