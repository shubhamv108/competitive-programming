package code.dynamicprogramming;

import java.util.stream.IntStream;

public class EditDistance {

    class Solution {
        private int editDistance (String A, String B, int n, int m) {
            if (m == 0) return n;
            if (n == 0) return m;
            if (A.charAt(n-1) == B.charAt(m-1))
                return editDistance(A, B, n-1, m-1);
            return 1 + Math.min(editDistance(A, B, n, m-1),
                                Math.min(editDistance(A, B, n-1, m),
                                         editDistance(A, B, n-1, m-1)));
        }

        public int minDistance(String A, String B) {
            //return editDistance (A, B, A.length(), B.length());
            int[][] dist = new int[A.length()+1][B.length()+1];
            for (int i=0; i <= A.length(); i++) {
                for (int j=0; j <= B.length(); j++) {
                    if (i == 0) dist[i][j] = j;
                    else if (j == 0) dist[i][j] = i;
                    else if (A.charAt(i-1) == B.charAt(j-1))
                        dist[i][j] = dist[i-1][j-1];
                    else dist[i][j] = 1 +  Math.min(dist[i][j-1],
                                Math.min(dist[i-1][j], dist[i-1][j-1]));
                }
            }

            IntStream.range(0, dist.length).forEach(i -> {
                IntStream.range(0, dist[0].length).forEach(j -> {
                    System.out.print(dist[i][j] + " ");
                });
                System.out.println();
            });
            return dist[A.length()][B.length()];
        }
    }

    class Solution3 {
        Integer[][] dp;
        public int minDistance(String word1, String word2) {
            this.dp = new Integer[word1.length()+1][word2.length()+1];
            return minDistance(word1.toCharArray(), word2.toCharArray(), word1.length(), word2.length());
        }
        public int minDistance(char[] a, char[] b, int n, int m) {
            if (m == 0) return dp[n][m] = n;
            if (n == 0) return dp[n][m] = m ;
            if (dp[n][m] != null) return dp[n][m];
            if (a[n-1] == b[m-1]) return dp[n][m] = minDistance(a, b, n-1, m-1);
            return dp[n][m] = 1 + Math.min(minDistance(a, b, n, m-1), Math.min(minDistance(a, b, n-1, m), minDistance(a, b, n-1, m-1)));
        }
    }

    public static void main(String[] args) {
        System.out.println(
        new EditDistance().new Solution().minDistance("abbcus", "bbacus"));
    }
}
