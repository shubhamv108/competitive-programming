package code.shubham.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsInGrid {

    class Solution {
        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        public List<String> findWords(char[][] board, String[] words) {
            List<String> result = new ArrayList<>();
            Node trie = new Node();
            Arrays.stream(words).forEach(trie::add);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    search(board, i, j, trie, result);
                }
            }
            return result;
        }

        void search(char[][] board, int x, int y, Node node, List<String> result) {
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;
            char c = board[x][y];
            if (c == '~' || node.next[c - 'a'] == null) return;
            node = node.next[c - 'a'];
            if (node.word != null) {
                result.add(node.word);
                node.word = null;
            }

            board[x][y] = '~';
            for (int[] direction : directions) {
                search(board, x + direction[0], y + direction[1], node, result);
            }
            board[x][y] = c;
        }

        class Node {
            Node[] next = new Node[26];
            String word;

            void add(String word) {
                insert(word, word.toCharArray(), 0);
            }

            void insert(String word, char[] w, int index) {
                if (index == w.length) {
                    this.word = word;
                    return;
                }
                if (next[w[index] - 'a'] == null) {
                    next[w[index] - 'a'] = new Node();
                }
                next[w[index] - 'a'].insert(word, w, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new WordsInGrid().new Solution().findWords(new char[][] {
                        {'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}
                }, new String[] {"oath","pea","eat","rain"})
        );
    }

}
