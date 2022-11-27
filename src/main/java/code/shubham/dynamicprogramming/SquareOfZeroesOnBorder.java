package code.shubham.dynamicprogramming;

public class SquareOfZeroesOnBorder {

    int[][] matrix;
    Pair[][] sums;

    class Pair {
        int x;
        int y;
        Pair(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    SquareOfZeroesOnBorder(int[][] matrix) {
        this.matrix = matrix;
        this.sums = computeSums();
    }

    Pair[][] computeSums() {
        Pair[][] sums = new Pair[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sums[i][j] = new Pair(sum += matrix[i][j]);
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sums[i][j].setY(sum += matrix[i][j]);
            }
        }
        return sums;
    }

    int getCount() {
        int result = 0;
        for (int startX = 0; startX < matrix.length; startX++) {
            for (int startY = 0; startY < matrix[startX].length; startY++) {
                if (matrix[startX][startY] == 0) {
                    for (int endX = startX+1; endX < matrix.length; endX++) {
                        for (int endY = startY+1; endY < matrix[endX].length; endY++) {
                            if (matrix[endX][endY] == 0) {
                                if (areBordersZero(startX, startY, endX, endY)) {
                                    result++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    boolean areBordersZero(int startRow, int startCol, int endRow, int endCol) {
        return
            getSumRow(startRow, startCol, endCol) +
            getSumRow(endRow, startCol, endCol) +
            getSumCol(startCol, startRow, endRow) +
            getSumRow(startCol, startRow, endRow)
            ==
            0;

    }

    int getSumRow(int row, int start, int end) {
        int result = matrix[row][end];
        if (start != 0) {
            result -= matrix[row][start-1];
        }
        return result;
    }

    int getSumCol(int col, int start, int end) {
        int result = matrix[end][col];
        if (start != 0) {
            result -= matrix[start-1][col];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new SquareOfZeroesOnBorder(
                        new int[][] {
                                {1,1,1,0,1,0},
                                {0,0,0,0,0,1},
                                {0,1,1,1,0,1},
                                {0,0,0,1,0,1},
                                {0,1,1,1,0,1},
                                {0,0,0,0,0,1},
                        }
                ).getCount()
        );
    }

}
