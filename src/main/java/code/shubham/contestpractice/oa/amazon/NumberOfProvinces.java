package code.shubham.contestpractice.oa.amazon;

import java.util.HashSet;

public class NumberOfProvinces {
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int result = 0, n = isConnected.length;
            HashSet<Integer> visited = new HashSet<>();
            for (int v = 0; v < n; v++)
                if (!visited.contains(v)) {
                    result++;
                    visit(v, isConnected, visited);
                }
            return result;
        }

        void visit(int v, int[][] isConnected, HashSet<Integer> visited) {
            if (visited.contains(v)) return;
            visited.add(v);
            for (int n = 0; n < isConnected.length; n++)
                if (isConnected[v][n] == 1)
                    visit(n, isConnected, visited);
        }
    }
}
