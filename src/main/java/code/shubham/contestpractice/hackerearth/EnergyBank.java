package code.shubham.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

public class EnergyBank {

    private static class InputUtils {

        private static BufferedReader BR;

        public static BufferedReader getBR() {
            if (null == BR) {
                BR = new BufferedReader(new InputStreamReader(in));
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
            return valueOf(s);
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
        int N = InputUtils.nextInt();
        String[] line = InputUtils.splitNextLine();
        BigInteger largest = BigInteger.ZERO;
        BigInteger acc = BigInteger.ZERO;
        for (String e : line) {
            BigInteger t = new BigInteger(e);
            if (t.compareTo(largest) > 0) largest = t;
            acc = acc.add(t);
        }
        System.out.println(acc.subtract(largest));
    }

}
