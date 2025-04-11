package code.shubham._01Knapsack.PalindromicSubsequence;

public class LongestPalindromicSubstring {
    class Solution {
        public String longestPalindrome(String s) {
            int[] result = new int[] {0, 0, 1};
            int max = -1;
            for (int i = 0; i < s.length() - 1; ++i) {
                int[] a = expand(s, i, i);
                int[] b = expand(s, i, i + 1);
                if (a[2] > result[2])
                    result = a;
                if (b[2] > result[2])
                    result = b;
            }
            return s.substring(result[0], result[1] + 1);
        }

        int[] expand(String s, int l, int r) {
            while (l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
            }
            return new int[] {l + 1, r - 1, r - l + 1};
        }
    }
}
