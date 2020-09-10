package code.codility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Charges {

    private class Solution {

        private Solution() {

        }

        private void solve() {

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
        Charges problemNameObject = new Charges();
        while (t-- > 0) {
             line = InputUtils.splitNextLine();
//            System.out.println(
                problemNameObject.new Solution().solve();
//            );
        }
    }
}


