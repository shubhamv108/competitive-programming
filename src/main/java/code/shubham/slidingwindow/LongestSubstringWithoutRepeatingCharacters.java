package code.shubham.slidingwindow;

public class LongestSubstringWithoutRepeatingCharacters {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int result = 0, start = 0;
            int[] pos = new int[128];
            for (int end = 0; end < s.length(); ++end) {
                char c = s.charAt(end);
                start = Math.max(start, pos[c]);
                result = Math.max(result, end - start + 1);
                pos[c] = end + 1;
            }
            return result;
        }
    }
}
