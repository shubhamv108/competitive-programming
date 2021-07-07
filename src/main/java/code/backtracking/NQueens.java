package code.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueens {

    public class Solution {

        private char[] r;
        private String emptyRow;
        private ArrayList<ArrayList<String>> result;
        public ArrayList<ArrayList<String>> solveNQueens(int a) {
            result = new ArrayList<>();
            if (a != 0) {
                r = new char[a];
                Arrays.fill(r, '.');
                emptyRow = String.valueOf(r);
                ArrayList<String> board = IntStream.range(0, a)
                        .mapToObj(i -> emptyRow)
                        .collect(Collectors.toCollection(ArrayList::new));
                solveNQueensUtil(board, 0);
            }
            return result;
        }

//        public void solveNQueensUtil(ArrayList<String> board, int col) {
//            if (col == board.size()) {
//                result.add(deepCopy(board));
//                return;
//            }
//            for (int i = 0; i < board.size(); i++) {
//                if (isSafe(board, i, col)) {
//                    board.set(i, setQueen(col));
//                    solveNQueensUtil(board, col + 1);
//                    board.set(i, removeQueen(col));
//                }
//            }
//        }

        public void solveNQueensUtil(ArrayList<String> board, int row) {
            if (row == board.size()) {
                result.add(new ArrayList<>(board));
                return;
            }
            for (int i = 0; i < board.size(); i++) {
                if (isSafe(board, row, i)) {
                    board.set(row, setQueen(i));
                    solveNQueensUtil(board, row + 1);
                    board.set(row, removeQueen(i));
                }
            }
        }

        private boolean isSafe(ArrayList<String> board, int row, int col) {
            return  // isRowSafe(board, row) &&
                    isColumnSafe(board, col) &&
                    isRightDiagonalSafe(board, row, col) &&
                    isLeftDiagonalSafe(board, row, col);
        }

        private boolean isRightDiagonalSafe(ArrayList<String> board, int row, int col) {
            for (int i = row - 1, j = col + 1; i >= 0 && j < board.size(); i--, j++) {
                if (board.get(i).charAt(j) == 'Q')
                    return false;
            }
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board.get(i).charAt(j) == 'Q')
                    return false;
            }
            return true;
        }

        private boolean isLeftDiagonalSafe(ArrayList<String> board, int row, int col) {
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board.get(i).charAt(j) == 'Q')
                    return false;
            }
            for (int i = row + 1, j = col - 1; i <= board.size() - 1 && j >= 0; i++, j--) {
                if (board.get(i).charAt(j) == 'Q')
                    return false;
            }
            return true;
        }

        private boolean isColumnSafe(ArrayList<String> board, int col) {
            boolean result = true;
            for (int i=0; i < board.size(); i++) {
                 if (board.get(i).charAt(col) == 'Q') {
                     result = false;
                     break;
                 }
            }
            return result;
        }

        private boolean isRowSafe(ArrayList<String> board, int row) {
            return board.get(row).equals(emptyRow);
        }

        public String setQueen(int col) {
            r[col] = 'Q';
            String s = String.valueOf(r);
            r[col] = '.';
            return s;
        }

        public String removeQueen(int col) {
            return emptyRow;
        }
    }

    public static void main(String[] args) {
        System.out.println("[");
        new NQueens().new Solution().solveNQueens(4)
                .stream()
                .forEach(a -> {
                    System.out.println("\t[");
                    a.stream().forEach(System.out::println);
                    System.out.println("\t]");
                });
        System.out.println("]");
    }

}
