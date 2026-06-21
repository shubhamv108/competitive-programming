package code.shubham.graphs.trees.dp.rerooting;

import java.util.ArrayList;
import java.util.Arrays;

public class SumOfDistancesInTree {
    class Solution {
        int[] dp, subtreeSize;
        ArrayList<Integer>[] g;
        int N;
        public int[] sumOfDistancesInTree(int N, int[][] A) {
            this.N = N;
            g = new ArrayList[N];
            dp = new int[N];
            subtreeSize = new int[N];
            Arrays.fill(subtreeSize, 1);

            for (int i = 0; i < N; ++i)
                g[i] = new ArrayList<>();
            for (int[] a: A) {
                g[a[0]].add(a[1]);
                g[a[1]].add(a[0]);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return dp;
        }

        public void dfs(int node, int parent) {
            for (int child: g[node]) {
                if (child == parent)
                    continue;
                dfs(child, node);
                subtreeSize[node] += subtreeSize[child];
                dp[node] += dp[child] + subtreeSize[child];
            }
        }

        public void dfs2(int node, int parent) {
            for (int child : g[node]) {
                if (child == parent)
                    continue;
                dp[child] = dp[node] - subtreeSize[child] + (N - subtreeSize[child]);
                dfs2(child, node);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new SumOfDistancesInTree()
            .new Solution()
            .sumOfDistancesInTree(6, new int[][] {
                    {0,1},
                    {0,2},
                    {2,3},
                    {2,4},
                    {2,5}
            }));
    }
}
