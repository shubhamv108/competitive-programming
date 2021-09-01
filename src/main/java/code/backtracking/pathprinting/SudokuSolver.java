package code.backtracking.pathprinting;

public class SudokuSolver {
    class Solution1 {

        int[][] board;

        Solution1(int[][] board) {
            this.board = board;
        }

        int[] findUnassigned() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == 0) return new int[]{i, j};
                }
            }
            return null;
        }

        boolean isRowSafe(int r, int n) {
            for (int i = 0; i < 9; i++)
                if (board[r][i] == n) return false;
            return true;
        }

        boolean isColumnSafe(int c, int n) {
            for (int i = 0; i < 9; i++)
                if (board[i][c] == n) return false;
            return true;
        }

        boolean isBoxSafe(int r, int c, int n) {
            int sr = (r / 3) * 3;
            int sc = (c / 3) * 3;
            for (int i = sr; i < sr + 3; i++) {
                for (int j = sc; j < sc + 3; j++) {
                    if (board[i][j] == n) return false;
                }
            }
            return true;
        }

        boolean isSafe(int r, int c, int n) {
            if (isRowSafe(r, n) && isColumnSafe(c, n) && isBoxSafe(r, c, n)) return true;
            return false;
        }

        boolean solveBoard() {
            int[] a = findUnassigned();
            if (a == null) {
                return true;
            } else {
                for (int i = 1; i <= 9; i++) {
                    if (isSafe(a[0], a[1], i)) {
                        board[a[0]][a[1]] = i;
                        if (solveBoard()) return true;
                    }
                }
                board[a[0]][a[1]] = 0;
            }
            return false;
        }

        void solve() {
            solveBoard();
        }
    }

    class Solution {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        boolean solve(char[][] board) {
            int[] a = findUnassigned(board);
            if (a == null) return true;
            else {
                for (int n = 1; n <= 9; n++) {
                    if (this.isSafe(board, a[0], a[1], n)) {
                        board[a[0]][a[1]] = (char) (n + 48);
                        if (solve(board)) return true;
                    }
                }
                board[a[0]][a[1]] = '.';
            }
            return false;
        }

        int[] findUnassigned(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') return new int[]{i, j};
                }
            }
            return null;
        }

        boolean isSafe(char[][] board, int r, int c, int n) {
            return isRowSafe(board, r, n) && isColSafe(board, c, n) && isBoxSafe(board, r, c, n);
        }

        boolean isRowSafe(char[][] board, int r, int n) {
            for (int i = 0; i < 9; i++)
                if (board[r][i] == (char) (n + 48)) return false;
            return true;
        }

        boolean isColSafe(char[][] board, int c, int n) {
            for (int i = 0; i < 9; i++)
                if (board[i][c] == (char) (n + 48)) return false;
            return true;
        }

        boolean isBoxSafe(char[][] board, int r, int c, int n) {
            r = (r / 3) * 3;
            c = (c / 3) * 3;
            for (int i = r; i < r + 3; i++)
                for (int j = c; j < c + 3; j++)
                    if (board[i][j] == (char) (n + 48)) return false;
            return true;
        }

    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        for(int i=0;i<9;i++)
//            for(int j=0;j<9;j++) board[i][j] = Integer.parseInt(sc.next());
        int[][] board={{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        new SudokuSolver().new Solution1(board).solve();
        for(int i=0; i<9;i++) {
            for(int j=0;j<9;j++) System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
}
