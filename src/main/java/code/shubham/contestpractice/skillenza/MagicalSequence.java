package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ToDo
 */
public class MagicalSequence {

    private class Solution {
        private String seq;

        private Solution(String seq) {
            this.seq = seq;
        }

        private int solve() {
            int result = 0;
            int sum = 0;
            int prev = (int) seq.charAt(0) - 48 - 1;
            for (int i = 0; i < seq.length(); i++) {
                int cur = Integer.valueOf(seq.charAt(i)) - 48;
                sum = 0;
                if ((i & 1) == 0) {
                    int dob = cur * 2;
                    if (dob > 10) {
                        dob = (dob / 10) + (dob % 10);
                    }
                    sum += dob;
                    if (sum > 10)
                    prev = cur;
                }
            }
            if (sum % 10 == 0) result++;
            return result;
        }

    }

    private static class InputUtils {

        private static BufferedReader BR;
        private static InputStreamReader inputStreamReader;

        public static BufferedReader getBR() {
            if (null == BR) {
                inputStreamReader = new InputStreamReader(System.in);
                BR = new BufferedReader(inputStreamReader);
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
        String[] line = null;
        int n;
        while (t-- > 0) {
            String seq = InputUtils.nextLine();
            System.out.println(
                new MagicalSequence().new Solution(seq).solve()
            );
        }
    }
}

