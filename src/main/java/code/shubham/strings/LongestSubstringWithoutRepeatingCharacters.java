package code.shubham.strings;

public class LongestSubstringWithoutRepeatingCharacters {
    class Solution {
        public int solve(String s) {
            int result = 0, curStart = -1;
            int[] p = new int[128];
            for (int i = 0; i < s.length(); ++i) {
                int c = s.charAt(i);
                if (p[c] >= curStart)
                    curStart = p[c] + 1;
                p[c] = i + 1;
                result = Math.max(result, i - curStart + 2);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().new Solution().solve("au"));
    }
}
