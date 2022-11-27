package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MissingDigit {

    private class Solution {
        private String n;
        public Solution(String n) {
            this.n = n;
        }
        private int solve() {
            int i = 0;
            int sum = 0;
            if ((n.length() & 1) == 0) { i = 1; }
            for (;i < n.length(); i+=2) {
                if ((i-1) > -1) sum+= Integer.valueOf(n.charAt(i-1) - '0');
                int d = Integer.valueOf(n.charAt(i) - '0');
                d*=2;
                sum+=((d%10)+(d/10))%10;
            }
            sum%=10;
            return 0 == sum ? 0 : 10 - sum;
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
        MissingDigit missingDigit = new MissingDigit();
        while(t-- > 0) {
            String n = InputUtils.nextLine();
            System.out.println(
                    missingDigit.new Solution(n).solve()
            );
        }
    }
}
