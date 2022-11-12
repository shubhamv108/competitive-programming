package code.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    class Solution {
        int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        public List<String> findWords(char[][] board, String[] words) {
            ArrayList<String> result = new ArrayList<>();
            Node root = new Node();
            for (String word : words)
                root.insert(word);

            int m = board.length, n = board[0].length;
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    search(board, r, c, m, n, root, result);

            return result;
        }

        void search(char[][] A, int r, int c, int m, int n, Node node, ArrayList<String> result) {
            if (r < 0 || c < 0 || r >= m || c >= n || A[r][c] == '#')
                return;

            int nextIndex = A[r][c] - 'a';
            if (node.next[nextIndex] == null)
                return;

            if (node.next[nextIndex].word != null) {
                result.add(node.next[nextIndex].word);
                node.next[nextIndex].word = null;
            }

            char t = A[r][c];
            A[r][c] = '#';
            for (int[] dir : dirs)
                search(A, r + dir[0], c + dir[1], m, n, node.next[nextIndex], result);
            A[r][c] = t;
        }



        class Node {
            String word;
            Node[] next = new Node[26];

            void insert(String A) {
                insert(A, A.toCharArray(), 0);
            }

            void insert(String word, char[] A, int index) {
                if (index == A.length) {
                    this.word = word;
                    return;
                }

                int nextIndex = A[index] - 'a';
                if (next[nextIndex] == null)
                    next[nextIndex] = new Node();
                next[nextIndex].insert(word, A, index+1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new WordSearchII().new Solution();
        solution.findWords(new char[][] { { 'a', 'a' } }, new String[] { "aa" }).
                forEach(System.out::println);
    }
}
