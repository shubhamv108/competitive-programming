package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ToDo
 */
public class FibonacciMadness {

    private class Solution {
        private int solve(final int p, final int fp, final int q, final int fq, final int r) {
            double phi = (1 + Math.sqrt(5)) / 2;
            return (int) Math.round(Math.pow(phi, r)
                    / Math.sqrt(5));
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
        int t = InputUtils.nextInt();
        FibonacciMadness fibonacciMadness = new FibonacciMadness();
        while(t-- > 0) {
            String[] line = InputUtils.splitNextLine();
            int p = Integer.valueOf(line[0]);
            int fp = Integer.valueOf(line[1]);
            line = InputUtils.splitNextLine();
            int q = Integer.valueOf(line[0]);
            int fq = Integer.valueOf(line[1]);
            int r = InputUtils.nextInt();
            System.out.println(fibonacciMadness.new Solution().solve(p, fp, q, fq, r));
        }
    }
}
