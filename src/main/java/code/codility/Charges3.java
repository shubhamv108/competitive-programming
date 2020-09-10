package code.codility;

public class Charges3 {

    private class Solution {

        private Solution() {

        }

        private void solve() {

        }

    }

    private static class InputUtils {

        private static java.io.BufferedReader BR;
        private static java.io.InputStreamReader inputStreamReader;

        public static java.io.BufferedReader getBR() {
            if (null == BR) {
                inputStreamReader = new java.io.InputStreamReader(System.in);
                BR = new java.io.BufferedReader(inputStreamReader);
            }
            return BR;
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, " ");
        }

        public static String[] splitNextLine(java.io.BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(java.io.BufferedReader br, String regex) {
            return nextLine().split(regex);
        }

        public static String nextLine() {
            return nextLine(getBR());
        }

        public static String nextLine(java.io.BufferedReader br) {
            try {
                return br.readLine();
            } catch (java.io.IOException e) {
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
        int t = code.codility.Charges3.InputUtils.nextInt();
        String[] line = null;
        int n;
        Charges3 problemNameObject = new Charges3();
        while (t-- > 0) {
            line = code.codility.Charges3.InputUtils.splitNextLine();
//            System.out.println(
            problemNameObject.new Solution().solve();
//            );
        }
    }
}