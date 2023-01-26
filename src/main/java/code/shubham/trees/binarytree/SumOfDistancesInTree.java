package code.shubham.trees.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumOfDistancesInTree {
    class Solution {
        int[] ans, count;
        ArrayList<HashSet<Integer>> graph;
        int N;
        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            this.N = N;
            graph = new ArrayList<>();
            ans = new int[N];
            count = new int[N];
            Arrays.fill(count, 1);

            for (int i = 0; i < N; ++i)
                graph.add(new HashSet<>());
            for (int[] edge: edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        public void dfs(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    dfs(child, node);
                    count[node] += count[child];
                    ans[node] += ans[child] + count[child];
                }
        }

        public void dfs2(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    ans[child] = ans[node] - count[child] + N - count[child];
                    dfs2(child, node);
                }
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
                new SumOfDistancesInTree().new Solution().
                        sumOfDistancesInTree(6, new int[][] {{0,1},{0,2},{2,3},{2,4},{2,5}})).
                forEach(System.out::println);
    }
}
