package code.shubham.contestpractice.oa.microsoft;

public class LongestSubstringWithoutTwoContiguousOccurrencesOfLetter {
    class Solution {

        public static String longestValidString(String s) {
            int len = s.length(), resultStart = 0, maxLength = 0, start = 0, count = 1;

            for (int i = 1; i < len; ++i) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    ++count;
                } else {
                    count = 1;
                }

                if (count <= 2) {
                    if (i - start + 1 > maxLength) {
                        maxLength = i - start + 1;
                        resultStart = start;
                    }
                } else {
                    start = i - 1;
                    count = 2;
                }
            }
            return s.substring(resultStart, maxLength);
        }
    }

}
