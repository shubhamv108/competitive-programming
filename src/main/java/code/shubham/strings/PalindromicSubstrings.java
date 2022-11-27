package code.shubham.strings;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 */
public class PalindromicSubstrings {

    class Solution {
        int count = 0;
        public int countSubstrings(String input) {
            if (input == null || input.length() == 0) return 0;
            char[] s = input.toCharArray();
            for (int i = 0; i < s.length; i++) { // i is the mid point
                countPalindromeSubstrings(s, i, i); // odd length;
                countPalindromeSubstrings(s, i, i + 1); // even length
            }
            return this.count;
        }

        void countPalindromeSubstrings(char[] s, int left, int right) {
            while (left >= 0 && right < s.length && s[left] == s[right]) {
                this.count++; left--; right++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PalindromicSubstrings().new Solution().countSubstrings("abaaba")
        );
    }

}
