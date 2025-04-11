package code.shubham._01Knapsack.PalindromicSubsequence;

import java.util.stream.IntStream;

public class PalindromicSubstrings {
    class Solution {
        public int countSubstrings(String s) {
            return IntStream.range(0, s.length())
                    .map(i -> expand(s, i, i) + expand(s, i, i + 1))
                    .sum();
        }

        int expand(String s, int l, int r) {
            int c = 0;
            while (l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++c;
            }
            return c;
        }
    }
}
