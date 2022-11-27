package code.shubham.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FormString {

    class Solution {
        boolean canBeFormed(String s, List<String> strings) {
            return canBeFormed(s, 0, new HashSet<>(strings), new Boolean[s.length()]);
        }

        private boolean canBeFormed(String s, int idx, HashSet<String> strings, Boolean[] dp) {
            if ("".equals(s)) return false;
            if (strings.contains(s)) return dp[idx] = true;
            if (dp[idx] != null) return dp[idx];
            String pre = "";
            for (int i = 0; i < s.length(); i++) {
                pre += s.charAt(i);
                if (strings.contains(pre)) {
                    strings.remove(pre);
                    if (canBeFormed(s.substring(i+1), idx + i, strings, dp))
                        return dp[idx] = true;
                    strings.add(pre);
                }
            }
            return dp[idx] = false;
        }
    }

    public static void main(String[] args) {
        FormString formString = new FormString();
        System.out.println(
        formString.new Solution().canBeFormed("cab", new ArrayList<>(Arrays.asList("ab", "aba", "c"))));
    }

}
