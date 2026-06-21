package code.shubham.graphs.bipartitegraph;

public class MaximumNumberOfAcceptedInvitations {
    class Solution {
        public int maximumInvitations(int[][] A) {
            int m = A.length, n = A[0].length, result = 0;
            Integer[] boyToGirl = new Integer[n];

            for (int boy = 0; boy < m; ++boy)
                if (dfs(A, n, boy, boyToGirl, new boolean[n+1]))
                    ++result;

            return result;
        }

        boolean dfs(int[][] A, int n, int boy, Integer[] boyToGirl, boolean[] visited) {
            for (int girl = 0; girl < n; ++girl)
                if (A[boy][girl] == 1 && !visited[girl]) {
                    visited[girl] = true;
                    if (boyToGirl[girl] == null || dfs(A, n, boyToGirl[girl], boyToGirl, visited)) {
                        boyToGirl[girl] = boy;
                        return true;
                    }
                }

            return false;
        }
    }
}
