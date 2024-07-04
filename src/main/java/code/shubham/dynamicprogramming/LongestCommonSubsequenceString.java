package code.shubham.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequenceString {
    class Solution {
        public List<int[]> lcs(String a, String b) {
            ArrayList<int[]> match = new ArrayList<>();
            lcs(a, b, 0, 0, 0, 0, new Integer[a.length()][b.length()], match);
            return match;
        }

        public int lcs(String a, String b, int as, int ae, int bs, int be, Integer[][] dp, ArrayList<int[]> match) {
            if (ae >= a.length() || be >= b.length())
                return 0;

            if (dp[ae][be] != null)
                return dp[ae][be];

            if (a.charAt(ae) == b.charAt(be)) {
                return dp[ae][be] = 1 + lcs(a, b, as, ae + 1, bs, be + 1, dp, match);
            }

            match.add(new int[] { as, Math.max(as, ae - 1), bs, Math.max(bs, be - 1) });
            return dp[ae][be] = Math.max(lcs(a, b, ae, ae, be + 1, be+1, dp, match), lcs(a, b, ae+1, ae + 1, be, be, dp, match));
        }
    }
}
