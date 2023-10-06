package code.shubham.strings;

import java.util.Arrays;

public class RankTeamsByVotes {
    class Solution {
        public String rankTeams(String[] A) {
            final int len = A[0].length();
            int[][] f = new int[26][len + 1];
            for (String a : A)
                for (int i = 0; i < len; i++)
                    f[a.charAt(i) - 'A'][i]++;

            for (int i = 0; i < 26; i++)
                f[i][len] = i;

            Arrays.sort(f, (x, y) -> {
                for (int i = 0; i < len; i++) {
                    if (x[i] == y[i])
                        continue;
                    return y[i] - x[i];
                }
                return 0;
            });

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 26; i++)
                result.append(f[i][len]);
            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new RankTeamsByVotes().new Solution().rankTeams(
                        new String[] {"ABC","ACB","ABC","ACB","ACB"}
                )
        );
    }
}
