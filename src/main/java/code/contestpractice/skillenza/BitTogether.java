package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitTogether {

    private class Solution {

        private int solve(final int l, final String s) {
            if (l < 2) return 0;
            char defaultChar = '1';
            int defaultCharCount = 0;
            int totalZeroes = 0;
            for (int i = 0; i < l; i++) {
                if (s.charAt(i) == '1')
                    defaultCharCount++;
                if (s.charAt(i) == '0')
                    totalZeroes++;
            }

            if (totalZeroes < defaultCharCount) {
                defaultCharCount = totalZeroes;
                defaultChar = '0';
            }

            int defaultCharStartingCount = 0;
            for (int i = 0; i < defaultCharCount ; i++)
                if (s.charAt(i) == defaultChar)
                    defaultCharStartingCount++;

            int defaultCharEndingCount = 0;
            for (int i = l-1; i > l - defaultCharCount - 1 ; i--)
                if (s.charAt(i) == defaultChar)
                    defaultCharEndingCount++;

            return defaultCharCount - Math.max(defaultCharStartingCount, defaultCharEndingCount);

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
        BitTogether bitTogether = new BitTogether();
        int t = InputUtils.nextInt();
        int l;
        String line;
        while(t-- > 0) {
            l = InputUtils.nextInt();
            line = InputUtils.nextLine();
            System.out.println(bitTogether.new Solution().solve(l, line));
        }
    }

}
