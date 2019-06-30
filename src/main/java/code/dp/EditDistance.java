package code.dp;

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
            return dist[A.length()][B.length()];
        }
    }
}
