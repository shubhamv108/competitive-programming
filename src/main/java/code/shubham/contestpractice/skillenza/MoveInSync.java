package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MoveInSync {

    private class Streak {
        char c;
        int h;
        int v;
        int ld;
        int rd;
        Streak(char c, int h, int v, int ld, int rd) {
            this.c = c;
            this.h = h;
            this.v = v;
            this.ld = ld;
            this.rd = rd;
        }
    }

    private class ResultStreak {
        int xStreaks = 0;
        int oStreaks = 0;
        void inc(char c, int by) {
            if ('x' == c)
                xStreaks += by;
            if ('o' == c)
                oStreaks += by;
        }
    }

    private class Solution {

        private int n, m, k;

        private Solution(int n, int m, int k) {
            this.n = n;
            this.m = m;
            this.k = k;
        }

        private ResultStreak resultStreak = new ResultStreak();

        private ResultStreak solve() {
            if (this.n < k && this.m < k) {
                for (int i = 0; i < this.n; i++)
                    InputUtils.nextLine();
                return resultStreak;
            }

            String[] line;
            Streak[] prevStreak, currStreak = null;
            for (int i = 0; i < this.n; i++) {
                prevStreak = currStreak;
                currStreak = new Streak[m];
                line = InputUtils.splitNextLine();
                for (int j = 0; j < this.m; j++)
                    updateCurrentStreak(line[j].charAt(0), i, j, currStreak, prevStreak, k);
            }
            return resultStreak;
        }

        private void updateCurrentStreak(char c, int i, int j, Streak[] currStreak, Streak[] prevStreak, int k) {
            int h, v, ld, rd;
            h = v = ld = rd = 1;

            if (isValid(i, j - 1) && c == currStreak[j-1].c)
                h = currStreak[j - 1].h + 1;

            if (isValid(i - 1, j) && c == prevStreak[j].c)
                v = prevStreak[j].v + 1;

            if (isValid(i-1, j-1) && c == prevStreak[j-1].c)
                ld = prevStreak[j-1].ld + 1;

            if (isValid(i-1, j+1) && c == prevStreak[j+1].c)
                rd = prevStreak[j+1].rd + 1;

            updateStreak(c, h, v, ld, rd, k);

            currStreak[j] = new Streak(c, h, v, ld, rd);
        }

        private void updateStreak(char c, int h, int v, int ld, int rd, int k) {
            int sum = 0;
            if (h >= k) sum++;
            if (v >= k) sum++;
            if (ld >= k) sum++;
            if (rd >= k) sum++;
           resultStreak.inc(c, sum);
        }

        private boolean isValid(int i, int j) {
            if (i < 0 || j < 0 || j >= this.m)
                return false;
            return true;
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
        MoveInSync moveInSync = new MoveInSync();
        int t = InputUtils.nextInt();
        String[] line;
        while(t-- > 0) {
            line = InputUtils.splitNextLine();
            int n = Integer.valueOf(line[0]);
            int m = Integer.valueOf(line[1]);
            int k = Integer.valueOf(line[2]);
            ResultStreak result = new MoveInSync().new Solution(n, m, k).solve();
            System.out.println(result.xStreaks + " " + result.oStreaks);
        }
    }
}

/**
 * Input:
 * 16
 * 3 3 3
 * x x x
 * x o x
 * x x x
 * 1 1 2
 * o
 * 2 2 2
 * x o
 * o x
 * 4 3 3
 * x x o
 * o x o
 * x o x
 * o x x
 * 1 5 2
 * x x x x x
 * 4 4 3
 * x x x x
 * x x o o
 * x x o o
 * x o o o
 * 4 4 2
 * x o o x
 * o x x o
 * o x x o
 * x o o x
 * 10 8 2
 * o x o x o o x o
 * o o x o x x o x
 * x o x x o x o x
 * x x o x x x o o
 * o x o x x x o x
 * o o o x o x o x
 * x o x o o o x o
 * x x o o x o x x
 * o x o x x x x o
 * x x o x o o o x
 * 1 9 3
 * x o o o o o x o o
 * 3 3 5
 * x x o
 * o o o
 * o o x
 * 10 8 5
 * o o x x x o x o
 * x o o o x o o o
 * x x o o o o x o
 * x o o o o x x x
 * x o x o x o x x
 * x x o o x o o x
 * o o x x o o o o
 * x x x x x o o o
 * x x o x x x o x
 * x x x o x x o o
 * 7 5 4
 * o x x o x
 * x x o o o
 * x o o o x
 * x x x o o
 * o x x o x
 * o o x x o
 * o x x x o
 * 2 1 3
 * o
 * o
 * 2 8 4
 * o x x x x x o o
 * o x o o x x o o
 * 6 8 2
 * o o x x x x x o
 * o o o x o x x x
 * x o x x x o x o
 * o o o o x x o x
 * x x o o x o o x
 * x o x x o o o o
 * 2 10 2
 * x x x o x o x x o o
 * o o o o o x x x x x
 *
 */

 /**
  * Output:
  * 4 0
  * 0 0
  * 1 1
  * 1 1
  * 4 0
  * 6 4
  * 10 8
  * 67 51
  * 0 3
  * 0 0
  * 5 9
  * 3 2
  * 0 0
  * 2 0
  * 39 40
  * 14 9
  */