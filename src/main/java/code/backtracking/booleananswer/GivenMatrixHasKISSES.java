package code.backtracking.booleananswer;

class MatrixHasWord {

    static boolean hasWordHelper(char[][] grid, int R, int C, int r, int c, char[] word, int wi, int n) {
        if (grid[r][c] == word[wi]) {
            wi++;
            if (wi > n)
                return true;
        } else wi = 0;
        if (r < R && hasWordHelper(grid, R, C, r + 1, c, word, wi, n))
            return true;
        else if (c < C) return hasWordHelper(grid, R, C, r, c + 1, word, wi, n);
        return false;
    }

    static boolean hasWord(char grid[][], char word[]) {
        char[] p = new char[word.length];
        return hasWordHelper(grid, grid.length - 1, grid[0].length - 1, 0, 0, word, 0, word.length - 1);
    }
}

public class GivenMatrixHasKISSES {

    public static void main(String[] args) {
        char[][] grid = {
                            {'A', 'S', 'H', 'D', 'E'},
                            {'J', 'S', 'U', 'T', 'F'},
                            {'M', 'H', 'B', 'H', 'A'},
                            {'O', 'U', 'B', 'H', 'K'},
                            {'T', 'H', 'U', 'A', 'M'}
        };
        System.out.println(new Solver().solve(grid, "SHUBHAM".toCharArray()));
    }

    public static class Solver {
        public boolean solve(char[][] grid, char[] word) {
            return MatrixHasWord.hasWord(grid,word);
        }
    }

    public static class Test {
//        @Test
        private void test1() {
            char[][] grid = {
                    {'A', 'S', 'H', 'D', 'E'},
                    {'J', 'S', 'U', 'T', 'F'},
                    {'M', 'H', 'B', 'H', 'A'},
                    {'O', 'U', 'B', 'H', 'K'},
                    {'T', 'H', 'U', 'A', 'M'}
            };
//            assertEquals(true, new Solver().solve(grid, "SHUBHAM".toCharArray()));
        }
    }
}
