package code.shubham.strings;

import java.util.Arrays;

public class ExtraCharactersInAString {
    class Solution {
        public int minExtraChar(String s, String[] A) {
            Node trie = new Node();
            Arrays.stream(A).forEach(a -> trie.add(a, 0));
            int i = 0, result = 0;
            while (i < s.length()) {
                int index = trie.search(s, i);
                System.out.println(index);
                if (index == -1) {
                    result++;
                    i++;
                } else  {
                    i = index + 1;
                }
                System.out.println("i=" + i);
            }
            return result;
        }

        class Node {
            boolean isEOW;
            Node[] next = new Node[26];

            void add(String w, int index) {
                if (w.length() == index) {
                    isEOW = true;
                    return;
                }

                int nextIndex = w.charAt(index) - 'a';

                if (next[nextIndex] == null)
                    next[nextIndex] = new Node();

                next[nextIndex].add(w, index + 1);
            }

            int search(String w, int index) {
                if (w.length() == index)
                    return isEOW ? w.length() - 1 : -1;

                int result = 0;
                if (isEOW)
                    result = index;

                int nextIndex = w.charAt(index) - 'a';
                if (next[nextIndex] == null)
                    return result - 1;

                int nextResult = next[nextIndex].search(w, index + 1);

                return nextResult == -1 ? result - 1 : nextResult;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new ExtraCharactersInAString().new Solution().minExtraChar(
                "dwmodizxvvbosxxw",
                    new String[] { "ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu" }
            )
        );
    }
}
