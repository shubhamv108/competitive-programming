package code.shubham.graphs.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeTheSpreadingFire {
    class Solution {
        int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        public int maximumMinutes(int[][] A) {
            int n = A.length - 1, m = A[0].length - 1;
            int h = toHouse(A, n, m);
            int hf = houseToFire(A, n, m);
            System.out.println(h + " " + hf);
            return Math.abs(h - hf);
        }

        int toHouse(int[][] A, int n, int m) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0,0});
            HashSet<String> v = new HashSet<>();
            v.add(0 + "-" + 0);
            int c = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] p = q.poll();
                    for (int[] d : dirs) {
                        int nr = p[0] + d[0], nc = p[1] + d[1];
                        if (n == p[0] && m == p[1])
                            return c + 1;
                        if (nr < 0 || nc < 0 || nr > n || nc > m || A[nr][nc] != 0 || !v.add(nr + "-" + nc))
                            continue;
                        q.offer(new int[] { nr, nc });
                    }
                }
                ++c;
            }
            return -1;
        }

        int houseToFire(int[][] A, int n, int m) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {n,m});
            HashSet<String> v = new HashSet<>();
            v.add(n + "-" + m);
            int c = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int[] p = q.poll();
                    for (int[] d : dirs) {
                        int nr = p[0] + d[0], nc = p[1] + d[1];
                        if (A[p[0]][p[1]] == 1)
                            return c + 1;
                        if (nr < 0 || nc < 0 || nr > n || nc > m || A[nr][nc] == 2 || !v.add(nr + "-" + nc))
                            continue;
                        q.offer(new int[] { nr, nc });
                    }
                }
                ++c;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new EscapeTheSpreadingFire().new Solution().maximumMinutes(new int[][] {
                {0,2,0,0,0,0,0},
                {0,0,0,2,2,1,0},
                {0,2,0,0,1,2,0},
                {0,0,2,2,2,0,2},
                {0,0,0,0,0,0,0}
        }));
    }
}
