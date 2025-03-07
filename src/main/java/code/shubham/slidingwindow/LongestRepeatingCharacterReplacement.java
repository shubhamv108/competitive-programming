package code.shubham.slidingwindow;

public class LongestRepeatingCharacterReplacement {
    class Solution {
        public int characterReplacement(String s, int k) {
            int start = 0, maxCount = 0, result = 0;
            int[] f = new int[58];

            for (int end = 0; end < s.length(); ++end) {
                int c = s.charAt(end) - 'A';
                maxCount = Math.max(maxCount, ++f[c]);
                while (end - start + 1 - maxCount > k)
                    --f[s.charAt(start++) - 'A'];
                result = Math.max(result, end - start + 1);
            }

            return result;
        }
    }
}
