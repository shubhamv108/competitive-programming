package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

public class One {

    private static int find (String s, int length)
    {
        int maxSubstringLength = 0;
        int sum = 0;
        int substringLength;
        Map<Integer, Integer> prev = new HashMap<>();

        for (int i = 0; i < length; i++) {

            if (s.charAt(i) == '0') sum++;
            else sum--;

            if (sum > 0) { maxSubstringLength = i + 1; }
            else {
                if ( prev.containsKey(sum - 1) ) {
                    substringLength = i - prev.get(sum - 1);
                    maxSubstringLength = Math.max(maxSubstringLength, substringLength);
                }
            }

            if ( !prev.containsKey(sum) ) prev.put(sum, i);
        }

        return maxSubstringLength;
    }

    public static void main(String[] args) {
        int N = InputUtils.nextInt();
        String s = InputUtils.nextLine();
        System.out.println(find(s, N));
    }


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

}
