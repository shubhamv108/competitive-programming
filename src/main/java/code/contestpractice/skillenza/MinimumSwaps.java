package code.contestpractice.skillenza;

import java.io.*;
import java.util.*;

public class MinimumSwaps {

    class Solution {
        public void solve() {
            String[] line = InputUtils.splitNextLine();


            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);

            List<Pos> midPos = midPos(m, n);

            int[][] arr = new int[m][n];
            int max = Integer.MIN_VALUE;
            int minDistFromMiddle = Integer.MAX_VALUE;
            for (int i = 0; i < m ; i++) {
                line = InputUtils.splitNextLine();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.valueOf(line[j]);
                    if (arr[i][j] > max) {
                        max = arr[i][j];
                        minDistFromMiddle = minDistFromMiddle(midPos, i, j);
                    } else if (arr[i][j] == max) {
                        minDistFromMiddle = Math.min(minDistFromMiddle, minDistFromMiddle(midPos, i, j));
                    }
                }
            }
            System.out.println(minDistFromMiddle);
        }

        private int minDistFromMiddle(List<Pos> midPos, int row, int column) {
            int minDist = Integer.MAX_VALUE;
            for (Pos p : midPos) {
                int dist = Math.abs(row - p.row) + Math.abs(column - p.column);
                minDist = Math.min(minDist, dist);
            }
            return minDist;
        }

        private List<Pos> midPos(int rowSize, int columnSize) {
            List<Pos> result = new ArrayList<>();
            int rowStart, rowEnd, columnStart, columnEnd;
            if ((rowSize & 1) == 0 && (columnSize  & 1) == 0) {
                rowStart = rowSize / 2;
                rowEnd = (rowSize/ 2) + 1;
                columnStart = columnSize / 2;
                columnEnd = (columnSize /2) + 1;
            } else if ((rowSize & 1) == 0 && (columnSize & 1) == 1) {
                rowStart = rowSize / 2;
                rowEnd = (rowSize/ 2) + 1;
                columnStart = (columnSize / 2) + 1;
                columnEnd = (columnSize / 2) + 1;
            } else if ((rowSize & 1) == 1 && (columnSize & 1) == 0) {
                rowStart = (rowSize/ 2) + 1;
                rowEnd = (rowSize/ 2) + 1;
                columnStart = columnSize / 2;
                columnEnd = (columnSize / 2) + 1;
            } else {
                rowStart = (rowSize / 2) + 1;
                rowEnd = (rowSize / 2) + 1;
                columnStart = (columnSize / 2) + 1;
                columnEnd = (columnSize / 2) + 1;
            }
            for (int i = rowStart; i <= rowEnd; i++) {
                for (int j = columnStart; j<= columnEnd; j++) {
                    result.add(new Pos(i-1, j-1));
                }
            }
            return result;
        }

    }

    class Pos {
        public int row;
        public int column;
        public Pos (int row, int column) {
            this.row = row;
            this.column = column;
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
        int t = Integer.valueOf(InputUtils.nextLine());
        MinimumSwaps minimumSwaps = new MinimumSwaps();
        while(t-- > 0) {
            minimumSwaps.new Solution().solve();
        }
    }
}


