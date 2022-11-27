package code.shubham.dynamicprogramming;

public class NonZeroMatrices {

    int[][] matrix;
    int[][] sumsFromOrigin;

    NonZeroMatrices(int[][] matrix) {
        this.matrix = matrix;
        this.sumsFromOrigin = sumOFRectangleFromOrigin(matrix);
    }

    // ((n*m)-count(1)) * ((n*m)-count(1)) = n*m*n*m
    int getCount() {
        int result = 0;
        for (int startX = 0; startX < matrix.length; startX++) {
            for (int startY = 0; startY < matrix[startX].length; startY++) {
                if (matrix[startX][startY] == 0) {
                    for (int endX = startX; endX < matrix.length; endX++) {
                        for (int endY = startY; endY < matrix[endX].length; endY++) {
                            if (matrix[endX][endY] == 0) {
                                if (startX == endX && startY == endY) {
                                    result++;
                                } else if (sum(startX, startY, endX, endY) == 0) {
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

    int sum(int startX, int startY, int endX, int endY) {
        return this.sumsFromOrigin[endX][endY]
                -
                (
                    this.sumsFromOrigin[startX][endY]
                    +
                    this.sumsFromOrigin[endX][startY]
                    -
                    this.sumsFromOrigin[startX][startY]
                );
    }

    // 2*n*m
    int[][] sumOFRectangleFromOrigin(int[][] matrix) {
        int[][] sums = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < sums.length; i++) {
            int sum = 0;
            for (int j = 0; j < sums[0].length; j++) {
                sums[i][j] = sum += matrix[i][j];
            }
        }
        for (int i = sums.length - 1; i > 0; i--) {
            for (int j = 0; j < sums[0].length; j++) {
                sums[i][j] = sums[i][j] + sums[i-1][j];
            }
        }
        return sums;
    }

    public static void main(String[] args) {
        System.out.println(
                new NonZeroMatrices(new int[][]{
                        {0,0},
                        {0,0}
                }).getCount()
        );
        System.out.println(
                new NonZeroMatrices(new int[][]{
                        {0,0,1,0,0},
                        {1,0,0,1,0},
                        {0,0,0,1,0},
                        {0,0,0,0,1},
                        {0,0,0,0,0}
                }).getCount()
        );
    }

}
