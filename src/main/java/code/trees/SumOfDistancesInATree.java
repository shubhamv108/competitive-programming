package code.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfDistancesInATree {

    class Solution {
        int n;
        int[] count, answer;
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            if (edges == null || edges.length == 0) return new int[] {0};
            this.n = n;
            for (int[] edge : edges) {
                add(edge[0], edge[1], true);
            }
            count = new int[n];
            Arrays.fill(count, 1);
            answer = new int[n];
            postorder(0, -1);
            preorder(0, -1);
            return answer;
        }

        void postorder(int node, int parent) {
            for (int child : graph.get(node)) {
                if (child != parent) {
                    postorder(child, node);
                    count[node] += count[child];
                    answer[node] += answer[child] + count[child];
                }
            }
        }

        void preorder(int node, int parent) {
            for (int child : graph.get(node)) {
                if (child != parent) {
                    answer[child] = answer[node] - count[child] + n - count[child];
                    preorder(child, node);
                }
            }
        }

        void add(int u, int v, boolean rev) {
            ArrayList<Integer> l = graph.get(u);
            if (l == null) graph.put(u, l = new ArrayList<>());
            l.add(v);
            if (rev)
                add(v, u, false);
        }
    }
}
