package code.backtracking;

public class IsWordExistsInGrid {

    class Solution {
        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        public boolean exist(char[][] board, String w) {
            int n = board.length, m = board[0].length;
            char[] word = w.toCharArray();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (exist(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean exist(char[][] board, int x, int y, char[] word, int index) {
            if (index == word.length) return true;
            if (x < 0 || y < 0 || x == board.length || y == board.length) return false;
            if (word[index] != board[x][y]) return false;
            boolean result = false;
            board[x][y] = '$';
            for (int[] direction : directions)
                if (exist(board, x + direction[0], y + direction[1], word, index + 1))
                    result = true;
            board[x][y] = word[index];
            return false;
        }

    }

}
