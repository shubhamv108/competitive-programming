package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ToDo
 */
public class GangsOfPrimesAndComposites {

    private class Pair {
        int prime;
        int comp;
    }

    private class Pos {
        private int i;
        private int j;
        private Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private class Solution {


        private int[][] arr;
        private int prime = 1;
        private int comp = 0;
        private final int VISITED = Integer.MIN_VALUE;

        private int primeCount = 0;
        private int compCount  = 0;

        private Solution(int[][] arr) {
            this.arr = arr;
        }

        private int[] get() {
            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == prime && isSafe(i, j, prime)) {
                        bfs(i, j, prime);
                        primeCount++;
                    }
                    if (arr[i][j] == comp  && isSafe(i, j, comp)) {
                        bfs(i, j, comp);
                        compCount++;
                    }
                }
            }
            return new int[] { primeCount, compCount };
        }

        private void bfs(int I, int J, int validityFor) {
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(I, J));
            arr[I][J] = VISITED;
            Pos t;
            while (!q.isEmpty()) {
                t = q.poll();
                for (int i = t.i - 1; i <= t.i + 1; i++) {
                    for (int j = t.j - 1; j <= t.j + 1; j++) {

                        if ((i == t.i && j == t.j)
                                || (i == t.i - 1 && j == t.j - 1)
                                || (i == t.i - 1 && i == t.j + 1)
                                || (i == t.i + 1 && j == t.j - 1)
                                || (i == t.i + 1 && i == t.j + 1))
                            continue;
                        if (isSafe(i, j, validityFor)) {
                            arr[i][j] = VISITED;
                            q.offer(new Pos(i, j));
                        }
                    }
                }
            }
        }

        public boolean isSafe(int i, int j, int validityFor) {
            return i > -1 && i < arr.length && j > -1 && j < arr[i].length && !isVisted(i, j) && isValid(i, j, validityFor);
        }

        public boolean isVisted(int i, int j) {
            return arr[i][j] == VISITED;
        }

        public boolean isValid(int i, int j, int validityFor) {
            return arr[i][j] == validityFor;
        }

    }

    private static class InputUtils {

        private static BufferedReader BR;

        public static BufferedReader getBR() {
            if (null == BR) {
                BR = new BufferedReader(new InputStreamReader(System.in));
            }
            return BR;
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, " ");
        }

        public static String[] splitNextLine(BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(BufferedReader br, String regex) {
            return nextLine().split(regex);
        }

        public static String nextLine() {
            return nextLine(getBR());
        }

        public static String nextLine(BufferedReader br) {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public static int toInteger(String s) {
            return Integer.valueOf(s);
        }

        public static long toLong(String s) {
            return Long.valueOf(s);
        }

        public static int nextInt() {
            return toInteger(nextLine());
        }

        public static long nextLong() {
            return toLong(nextLine());
        }

    }

    public static void main(String[] args) {
        GangsOfPrimesAndComposites gangsOfPrimesAndComposites = new GangsOfPrimesAndComposites();
        int t = InputUtils.nextInt();
        String[] line;
        System.out.println();
        while (t-- > 0) {
            line = InputUtils.splitNextLine();
            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);
            int[][] arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                line = InputUtils.splitNextLine();
                for (int j = 0; j < n; j++)
                    arr[i][j] = new BigInteger(line[j]).isProbablePrime(100) ? 1 : 0;
            }
            int[] result = gangsOfPrimesAndComposites.new Solution(arr).get();
            System.out.println(
                    result[0] + " " + result[1]
            );
        }
    }

}
