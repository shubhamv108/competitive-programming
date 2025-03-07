package code.shubham.slidingwindow;

public class LongestSubstringWithAtMostTwoDistinctCharacters {

    class Solution {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int start = 0, k = 2, result = 0;
            int[] f = new int[58];
            for (int end = 0; end < s.length(); ++end) {
                int c = s.charAt(end) - 'A';
                if (f[c]++ == 0) {
                    while (k == 0)
                        if (--f[s.charAt(start++) - 'A'] == 0)
                            ++k;
                    --k;
                }
                result = Math.max(result, end - start + 1);
            }

            return result;
        }
    }

}
