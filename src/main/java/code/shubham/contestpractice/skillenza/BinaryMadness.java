package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class BinaryMadness {

    private class Solution {
        private String solve(final BigInteger n) {
            long totalOddOneSubstrings = 0;
            long totalOddZeroSubstrings = 0;

            if (n.equals(BigInteger.ZERO)) return 1 + " " + totalOddOneSubstrings;

            final String binary = n.toString(2);
            int prevZeroCount, prevOneCount;


            for (int i = 0; i < binary.length(); i++) {
                prevOneCount = 0;
                prevZeroCount = 0;
                for (int j = i; j < binary.length(); j++) {
                    if (binary.charAt(j) == '1')
                        prevOneCount++;
                    else
                        prevZeroCount++;
                    if ((prevOneCount & 1) == 1)
                        totalOddOneSubstrings++;
                    if ((prevZeroCount & 1) == 1)
                        totalOddZeroSubstrings++;

                }
            }

            return totalOddZeroSubstrings + " " + totalOddOneSubstrings;
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
        BinaryMadness binaryMadness = new BinaryMadness();
        int t = InputUtils.nextInt();
        String line;
        while(t-- > 0) {
            line = InputUtils.nextLine();
            System.out.println(binaryMadness.new Solution().solve(new BigInteger(line)));
        }
    }
}
