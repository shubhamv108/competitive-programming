package code.contestpractice.oa.swiggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SwiggyPantry {

    public static void main(String[] args) {
        int T = InputUtils.nextInt();
        while (T-- > 0) {
            int N = InputUtils.nextInt();
            int[] cord = InputUtils.nextLineIntArray();
            System.out.println(get(cord[0], cord[1], cord[2], cord[3]));
        }
    }
    static int get(int a, int b, int x, int y) {
        if (a == x && b == y) return 0;
        if (!(((a + b) & 1) == 0 && ((x+y) & 1) == 0)
                && !(((a + b) & 1) == 0 && ((x+y) & 1) == 0))
            return -1;
        if (a + b == x + y) return 0;
        else return 2;
    }

    private static class InputUtils {
        private static BufferedReader BR;
        private static InputStreamReader inputStreamReader;
        static {
            BR = getBR();
        }
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
        public static long[] nextLinelongArray() {
            return Arrays.stream(splitNextLine()).mapToLong(Long::valueOf).toArray();
        }
        public static double[] nextLinedoubleArray() {
            return Arrays.stream(splitNextLine()).mapToDouble(Double::valueOf).toArray();
        }
        public static Integer[] nextLineIntegerArray() {
            return Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        }
        public static Long[] nextLineLongArray() {
            return Arrays.stream(splitNextLine()).mapToLong(Long::valueOf).boxed().toArray(Long[]::new);
        }
        public static Double[] nextLineDoubleArray() {
            return Arrays.stream(splitNextLine()).mapToDouble(Double::valueOf).boxed().toArray(Double[]::new);
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

}
