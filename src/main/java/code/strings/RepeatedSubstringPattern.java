package code.strings;

public class RepeatedSubstringPattern {
    class Solution {
        public boolean repeatedSubstringPattern(String str) {
            String s = str + str;
            return s.substring(1, s.length() - 1).contains(str);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new RepeatedSubstringPattern().new Solution().repeatedSubstringPattern("abab")
        );
    }
}
