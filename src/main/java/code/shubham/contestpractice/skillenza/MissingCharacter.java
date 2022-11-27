package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MissingCharacter {

    private class Solution {
        private char solve(final String s, final int length) {
            int sum = 0;
            for (int i = 0; i < length; i++) {
                int val = value(s.charAt(i));
                if (((length & 1) == 1 && (i & 1) == 0)
                    || ((length & 1) == 0 && (i & 1) == 1)) {
                    val = convert(val*2);
                }
                sum += val;
            }
            return character(64 - (sum % 64));
        }

        private int convert (int val) {
            return ((Double) Math.floor(val / 64)).intValue() + (val % 64);
        }

        private int value(char c) {
            int result = -1;
            int ascii = (int) c;
            if (ascii > 64 && ascii < 91) {
                result = ascii - 65;
            } else if (ascii > 96 && ascii < 123) {
                result = ascii - 71;
            } else if (ascii > 47 && ascii < 59) {
                result = ascii + 4;
            } else if (c == '+') {
                result = 62;
            } else {
                result = 63;
            }
            return result;
        }

        private char character(int n) {
            char result = 'A';
            if (n >= 0 && n <= 25) {
                result = (char) (n+65);
            } else if (n >= 26  && n <= 51) {
                result = (char) (n + 71);
            } else if (n >= 52 && n <= 61) {
                result = (char) (n - 4);
            } else if (n == 62) {
                result = '+';
            } else if (n == 63){
                result = '/';
            }
            return result;
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
        MissingCharacter numberOfPies = new MissingCharacter();
        while(t-- > 0) {
            int l = InputUtils.nextInt();
            String s = InputUtils.nextLine();
            System.out.println(numberOfPies.new Solution().solve(s, l));
        }
    }
}
