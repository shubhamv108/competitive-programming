package code.contestpractice.hackerearth.lenskart.jan19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

public class Two {

    private static Map<Integer, Long> K = new HashMap<>();
    private static int mod = 1000000007;

    private static long getK(int N) {
        if (!K.containsKey(N)) K.put(N, (long) (Math.pow(2, N) - Math.pow(2, N-1)));
        return K.get(N);
    }

    public static void main(String[] args) {
        int T = InputUtils.nextInt();
        int N;
        String[] line;
        while (T-- > 0) {
            N = InputUtils.nextInt();
            line = InputUtils.splitNextLine();
            long K = getK(N);

            System.out.println(
                    Arrays.stream(line).
                            map(Long::parseLong).
                            filter(a -> a >= K).
                            reduce(0L, (acc, a) -> acc = (acc + a) % mod));
        }
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
