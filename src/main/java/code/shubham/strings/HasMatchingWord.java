package code.shubham.strings;

import java.util.ArrayList;
import java.util.List;

public class HasMatchingWord {
    public class Solution {
        private class Node {
            boolean isEOW = false;
            List<String> words = new ArrayList<>();
            Node[] next = new Node[26];

            void add(String s, int idx) {
                words.add(s);
                if (s.length() == idx) {
                    isEOW = true;
                    return;
                }

                int ch = s.charAt(idx) - 'a';
                if (next[ch] == null)
                    next[ch] = new Node();
                next[ch].add(s, idx + 1);
            }

            boolean isPresent(String s, int idx) {
                if (s.length() <= idx)
                    return isEOW;

                char ch = s.charAt(idx);
                if (ch == '.') {
                    for (int i = 0; i < 26; ++i)
                        if (next[i] != null && next[i].isPresent(s, idx + 1))
                            return true;
                    return false;
                } else if (ch == '*') {
                    if (isPresent(s, idx + 1))
                        return true;
                    for (int i = 0; i < 26; ++i) {
                        if (next[i] == null)
                            continue;
                        if (next[i].isPresent(s, idx))
                            return true;
                    }
                    return false;
                } else {
                    int n = ch - 'a';
                    if (next[n] == null)
                        return false;
                    return next[n].isPresent(s, idx + 1);
                }
            }
        }

        private final Node trie = new Node();

        public Solution (String[] A) {
            for (String a : A)
                trie.add(a, 0);
        }

        public void addWord(String word) {
            trie.add(word, 0);
        }

        boolean isPresent(String pattern) {
            return trie.isPresent(pattern, 0);
        }
    }


    public static void main(String[] args) {
        Solution solution = new HasMatchingWord().new Solution(
                new String[] {
                        "abc"
                }
        );

        System.out.println(solution.isPresent("abc"));
        System.out.println(solution.isPresent("adc"));
        System.out.println(solution.isPresent("ab."));
        System.out.println(solution.isPresent("a.c"));
        System.out.println(solution.isPresent(".bc"));
        System.out.println(solution.isPresent("*"));
        System.out.println(solution.isPresent("a*"));
        System.out.println(solution.isPresent("ab*"));
        System.out.println(solution.isPresent("a*c"));
        System.out.println(solution.isPresent("b*"));
        System.out.println(solution.isPresent("*"));
        System.out.println(solution.isPresent("*c"));
        System.out.println(solution.isPresent("*d"));
        System.out.println(solution.isPresent("*bc"));
        System.out.println(solution.isPresent("*c"));
        System.out.println(solution.isPresent("*d"));
    }
}
