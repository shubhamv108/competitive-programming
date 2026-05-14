package code.shubham.trie;

import java.util.Arrays;

public class CountValidWordOccurrences {
    class Solution {
        public int[] countWordOccurrences(String[] A, String[] Q) {
            int n = A.length;
            Node trie = new Node();

            Node cur = trie;
            for (int i = 0; i < n; ++i) {
                Idx idx = new Idx();
                while (idx.idx < A[i].length()) {
                    cur = cur.add(A[i], idx, A[i].length());
                    if (cur == null)
                        cur = trie;
                }
            }

            if (cur != trie)
                ++cur.count;

            int qn = Q.length;
            int[] result = new int[qn];
            for (int i = 0; i < qn; ++i)
                result[i] = trie.search(Q[i], 0);

            return result;
        }

        class Idx {
            int idx;
        }

        class Node {
            int count;
            Node[] next = new Node[26];
            Node hyphen;

            Node add(String s, Idx index, int l) {
                int idx = index.idx;
                ++index.idx;
                ++count;
                if (idx == l) {
                    return this;
                }

                char c = s.charAt(idx);
                if (c == ' ') {
                    return null;
                } else if (c == '-') {
                    if (hyphen == null)
                        hyphen = new Node();

                    return hyphen.add(s, index, l);
                }

                int nextIdx = c - 'a';
                if (next[nextIdx] == null)
                    next[nextIdx] = new Node();

                return next[nextIdx].add(s, index, l);
            }

            int search(String s, int idx) {
                if (idx == s.length())
                    return count;

                char a = s.charAt(idx);
                if (a == '-')
                    return hyphen == null ? 0 : hyphen.search(s, idx + 1);

                int nextIdx = a - 'a';
                if (next[nextIdx] == null)
                    return 0;

                return next[nextIdx].search(s, idx + 1);
            }
        }
    }

    class Solution2 {
        public int[] countWordOccurrences(String[] A, String[] Q) {
            StringBuilder sb = new StringBuilder();
            for (String a : A) {
                sb.append(a);
            }
            int qn = Q.length;
            int[] result = new int[qn];
            for (int i = 0; i < qn; ++i) {
                int idx = -1, count = 0;
                while (true) {
                    idx = sb.indexOf(Q[i], idx + 1);
                    if (idx == -1)
                        break;
                    ++count;
                }
                result[i] = count;
            }

            return result;
        }
    }

    void main() {
//        System.out.println(
//                Arrays.toString(new CountValidWordOccurrences().new Solution().countWordOccurrences(
//                        new String[] { "hello wor","ld hello" },
//                        new String[] { "hello","world","wor" }
//                ))
//        );
        System.out.println(
                Arrays.toString(new CountValidWordOccurrences().new Solution2().countWordOccurrences(
                        new String[] { "a--b a-","-c" },
                        new String[] { "a","b","c" }
                                                                                                   ))
                  );
    }
}
