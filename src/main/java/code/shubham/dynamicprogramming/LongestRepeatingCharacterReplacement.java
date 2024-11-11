package code.shubham.dynamicprogramming;

public class LongestRepeatingCharacterReplacement {
// wrong approach
//    class Solution {
//        public int characterReplacement(String s, int k) {
//            int result = 1;
//            Integer[][] dp = new Integer[s.length()][k+1];
//            for (int i = 0; i < s.length() - 1; ++i)
//                result = Math.max(result, recurse(s, i, 1, s.charAt(i), k, 0, dp));
//            for (int i = s.length() - 1; i > 0; --i)
//                result = Math.max(result, recurse(s, i, -1, s.charAt(i), k, 0, dp));
//            return result;
//        }
//
//        int recurse(String s, int si, int nextIndex, char prevChar, int k, int curLength, Integer[][] dp) {
//            if (si == s.length() || si == -1)
//                return curLength;
//
//            if (s.charAt(si) == prevChar) {
//                return dp[si][k] = recurse(s, si + nextIndex, nextIndex, s.charAt(si), k, curLength + 1, dp);
//            } else if (k > 0) {
//                return dp[si][k] = recurse(s, si + nextIndex, nextIndex, prevChar, k - 1, curLength + 1, dp);
//            }
//
//            return dp[si][k] = curLength;
//        }
//    }

    class Solution {
        public int characterReplacement(String s, int k) {
            int result = 1, maxFreq = 0;
            int[] freq = new int[26];
            int l = 0;
            for (int r = 1; r < s.length(); ++r) {
                maxFreq = Math.max(maxFreq, ++freq[s.charAt(r) - 'A']);
                while (r - l + 1 - maxFreq > k)
                    --freq[s.charAt(l++) - 'A'];
                result = Math.max(result, r - l + 1);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharacterReplacement().new Solution().characterReplacement("BAAAB", 2));
    }
}
