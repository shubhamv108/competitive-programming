package code.strings;

import java.util.HashSet;
import java.util.Set;

public class CountDifferentPalindromicSubstring {

    class Solution {
        public int countPalindromicSubsequences(String s) {
            Set<String> set=new HashSet<>();
            char[] c = s.toCharArray();
            palindromicSubsequences(c, 0, new StringBuilder(), set);
            return set.size() - 1;
        }
        private void palindromicSubsequences(char[] c, int i, StringBuilder subsquence, Set<String> set) {
            if(i == c.length) {
                if(isPalindromic(subsquence)) set.add(subsquence.toString());
                return;
            }
            char t = c[i];
            palindromicSubsequences(c, i + 1, subsquence, set);
            palindromicSubsequences(c, i + 1, subsquence.append(c[i]), set);
            subsquence.deleteCharAt(subsquence.length() - 1);
        }
        private boolean isPalindromic(StringBuilder s){
            int l = 0, r = s.length() - 1;
            while(l < r && s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            return l >= r;
        }
    }

    public static void main(String[] args) {

    }

}
