package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.valueOf;
import static java.lang.Math.pow;
import static java.lang.System.in;

public class Three {

    class Pair {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int mod = 1000000007;

    Set<Pair> pairs = new HashSet<>();

    public static void main(String[] args) {
        int T = InputUtils.nextInt();
        String[] line;
        int a, b, c, d, m, N, count, ab;
        while (T-- > 0) {
            line = InputUtils.splitNextLine();
            a = Integer.valueOf(line[0]);
            b = Integer.valueOf(line[1]);
            c = Integer.valueOf(line[2]);
            d = Integer.valueOf(line[3]);
            m = Integer.valueOf(line[4]);
            N = InputUtils.nextInt();
            line = InputUtils.splitNextLine();
            Integer[] n = Arrays.stream(line).map(Integer::parseInt).toArray(Integer[]::new);
            count = 0;
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++) {
                    if (i == j) continue;
                    if (isValidPair(a, b, c, d, m, n, i, j)) count = (count + 1) % mod;
                }
            }
            System.out.println(count);
        }
    }

    private static boolean isValidPair(int a, int b, int c, int d, int m, Integer[] n, int i, int j) {
        double lhs = pow(n[j], 2) % m;
        double rhs = ((n[i] * ((n[i] * ((n[i] * a) + b)) + c)) + d) % m;
        return lhs == rhs;
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
