import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AVarietyOfOperations {
    private class Solution {

        private Solution() {

        }

        private int solve(int[] A) {
            if (A[0] == A[1]) return A[0] == 0 ? 0 : 1;
            if (((A[0] + A[1]) & 1) == 1) return -1;
            else return 2;
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

        public static int[] nextLineIntArray() {
            return Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).toArray();
        }

        public static Integer[] nextLineIntegerArray() {
            return Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, "\\s");
        }

        public static String[] splitNextLine(BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(BufferedReader br, String regex) {
            return nextLine(br).split(regex);
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

    private static class OutputUtils {
        public static void print(Object o) {
            System.out.print(o);
        }

        public static void println(Object o) {
            System.out.println(o);
        }
    }

    private static void print(Object o) {
        OutputUtils.print(o);
    }

    private static void println(Object o) {
        OutputUtils.println(o);
    }

    public static void main(String[] args) {
        int t = InputUtils.nextInt();
        AVarietyOfOperations object = new AVarietyOfOperations();
        while (t-- > 0) {
             int[] line = InputUtils.nextLineIntArray();
             println(object.new Solution().solve(line));
        }
    }
}

