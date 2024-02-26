package code.shubham.strings;

import java.util.Arrays;

public class UniqueLength3PalindromicSubsequences {
    class Solution {
        public int countPalindromicSubsequence(String s) {
            int result = 0, first[] = new int[26], last[] = new int[26];
            Arrays.fill(first, Integer.MAX_VALUE);
            for (int i = 0; i < s.length(); ++i) {
                first[s.charAt(i) - 'a'] = Math.min(first[s.charAt(i) - 'a'], i);
                last[s.charAt(i) - 'a'] = i;
            }
            for (int i = 0; i < 26; ++i)
                if (first[i] < last[i])
                    result += s.substring(first[i] + 1, last[i]).chars().distinct().count();
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new UniqueLength3PalindromicSubsequences()
                    .new Solution()
                    .countPalindromicSubsequence("bbcbaba")
        );
    }
}
