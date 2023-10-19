package code.shubham.trees;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ValidateBinaryTreeNodes {
    class Solution {
        public boolean validateBinaryTreeNodes(int n, int[] L, int[] R) {
            final boolean[] visited = new boolean[n];
            recurse(0, L, R, visited);

            return !IntStream.range(0, visited.length)
                    .mapToObj(i -> visited[i])
                    .anyMatch(e -> !e);
        }

        void recurse(int a, int[] L, int[] R, boolean[] visited) {
            if (visited[a])
                return;
            if (L[a] != -1)
                recurse(L[a], L, R, visited);
            if (R[a] != -1)
                recurse(L[a], L, R, visited);
        }
    }
}
