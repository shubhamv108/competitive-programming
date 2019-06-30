package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

import static java.lang.Integer.valueOf;
import static java.lang.Math.min;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * 9 9
 * 010101010
 * 101010101
 * 010101010
 * 101010101
 * 010101010
 * 101010101
 * 010101010
 * 101010101
 * 010101010
 */
public class ValidChessBoard {

    private static int N, M;
    private static String[][] chessBoardMatrix;

    private static int[][] m;
    private static int countOfValidChessBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String[] line = br.readLine().split(" ");
        N = valueOf(line[0]);
        M = valueOf(line[1]);
        if (N < 8 || M < 8) { out.println(0); return; }
        m = new int[N][M];
        initialize(m);
        chessBoardMatrix = new String[N][M];
        IntStream.range(0, N).forEach( r -> chessBoardMatrix[r] = splitNextLine(br) );
        processMatrix();
        IntStream.range(0, m.length).
                forEach( i -> {
                    IntStream.range(0, m[0].length).
                    forEach( j -> System.out.print(m[i][j]) );
                    System.out.println();
                });
        out.println(countOfValidChessBoard);
    }

    private static void processMatrix() {
        for (int i=1;i<N+1;i++) {
            for (int j = 1; j < M+1; j++) {
                m[i][j] = 1 + cellUsability(i, j);
                if (8 <= m[i][j]) {
                    countOfValidChessBoard++;
                }
            }
        }
    }

    private static int cellUsability(int i, int j) {
        if (validCell(chessBoardMatrix[i][j],
                chessBoardMatrix[i-1][j-1],
                chessBoardMatrix[i-1][j],
                chessBoardMatrix[i][j-1])) {
            return min(m[i-1][j-1], min(m[i-1][j], m[i][j-1]));
        }
        return 0;
    }

    private static String[] splitNextLine(BufferedReader br) {
        try {
            return br.readLine().split("");
        } catch (IOException e) {
            return null;
        }
    }

    private static void initialize(int[][] m) {
        IntStream.range(0, m[0].length).forEach(i -> m[0][i] = 1);
        IntStream.range(0, m.length).   forEach(i -> m[i][0] = 1);
    }

    private static boolean validCell(String n, String d, String u, String left) {
        if (isOne(n)  && isZero(u) && isZero(left) && isOne(d))  return true;
        if (isZero(n) && isOne(u) && isOne(left)   && isZero(d)) return true;
        return false;
    }

    private static boolean isZero(String s) {
        return "0".equals(s);
    }

    private static boolean isOne(String s) {
        return "1".equals(s);
    }
}
