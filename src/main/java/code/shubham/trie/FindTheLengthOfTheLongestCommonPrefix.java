package code.shubham.trie;

public class FindTheLengthOfTheLongestCommonPrefix {
    class Solution {
        public int longestCommonPrefix(int[] A, int[] B) {
            Node trie = new Node();
            for (int a : A) {
                String s = String.valueOf(a);
                trie.add(s, 0, s.length());
            }

            int result = 0;
            for (int b : B) {
                String s = String.valueOf(b);
                result = Math.max(result, trie.prefix(s, 0, s.length()));
            }

            return result;
        }

        class Node {
            Node[] next = new Node[10];

            void add(String A, int idx, int al) {
                if (idx == al)
                    return;

                int d = A.charAt(idx) - '0';
                if (next[d] == null)
                    next[d] = new Node();

                next[d].add(A, idx + 1, al);
            }

            int prefix(String A, int idx, int al) {
                if (idx == al)
                    return idx;

                int d = A.charAt(idx) - '0';
                if (next[d] == null)
                    return idx;

                return next[d].prefix(A, idx + 1, al);
            }
        }
    }

    void main() {
        System.out.println(new FindTheLengthOfTheLongestCommonPrefix().new Solution().longestCommonPrefix(
                new int[] { 1, 10, 100 },
                new int[] { 1000 }
                                                                                                         ));
    }
}
