package code.shubham.strings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int len = s.length();
            Set<String> dict = new HashSet<>(wordDict);

            boolean[] dp = new boolean[len + 1];
            dp[0] = true;

            for (int i = 1; i <= len; i++)
                for (int j = 0; j < i; j++)
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }

            return dp[len];
        }
    }
}
