package code.shubham.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    class Solution {
        int[][] dirs = {{-1, 0}, {0,  1}, {1, 0}, {0, -1}};
        class Node {
            String isEOW;
            Node[] next = new Node[26];

            void add(String word, int idx) {
                if (idx == word.length()) {
                    isEOW = word;
                    return;
                }
                int nextIdx = word.charAt(idx) - 97;
                if (next[nextIdx] == null)
                    next[nextIdx] = new Node();
                next[nextIdx].add(word, idx + 1);
            }
        }


        public List<String> findWords(char[][] A, String[] words) {
            ArrayList<String> result = new ArrayList();

            Node trie = new Node();
            for (String w : words)
                trie.add(w, 0);

            for (int i = 0; i < A.length; ++i)
                for (int j = 0; j < A[0].length; ++j)
                    visit(A, i, j, trie, result);
            return result;
        }

        void visit(char[][] A, int i, int j, Node node, ArrayList<String> result) {
            if (i < 0 || j < 0 || i == A.length || j == A[0].length || A[i][j] == ' ')
                return;

            Node cur = node.next[A[i][j] - 97];
            if (cur == null)
                return;

            if (cur.isEOW != null) {
                result.add(cur.isEOW);
                cur.isEOW = null;
            }

            char t = A[i][j];
            A[i][j] = ' ';
            for (int[] dir : dirs)
                visit(A, i + dir[0], j + dir[1], cur, result);
            A[i][j] = t;
        }
    }

    public static void main(String[] args) {
        Solution solution = new WordSearchII().new Solution();
        solution.findWords(new char[][] { { 'a', 'a' } }, new String[] { "aa" }).
                forEach(System.out::println);
    }
}
