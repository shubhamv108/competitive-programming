package code.shubham.strings;

import java.util.Set;

public class DetermineIfStringHalvesAreAlike {
    class Solution {
        public boolean halvesAreAlike(String S) {
            Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
            char[] s = S.toCharArray();
            int a = 0, b = 0;
            for (int l = 0, r = S.length() - 1; l < r; l++, r--) {
                a += vowels.contains(s[l]) ? 1 : 0;
                b += vowels.contains(s[r]) ? 1 : 0;
            }
            return a == b;
        }
    }
}
