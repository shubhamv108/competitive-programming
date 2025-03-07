package code.shubham.slidingwindow;

public class LongestSubstringWithAtMostKDistinctCharacters {

    class Solution {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int start = 0, result = 0;

            int[] f = new int[256];
            for (int end = 0; end < s.length(); ++end) {
                int c = s.charAt(end);
                if (f[c]++ == 0) {
                    while (k == 0)
                        if (--f[s.charAt(start++)] == 0)
                            ++k;
                    --k;
                }

                result = Math.max(result, end - start + 1);
            }

            return result;
        }
    }

}
