package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

class Tree {
    private Map<Integer, Long> tree = new HashMap<>();

    public void edge(int u, long v) {
        if (tree.containsKey(u)) tree.put(u, tree.get(u) + v);
        else tree.put(u, v);
    }

    public Map<Integer, Long> get() {
        return tree;
    }
}

public class PrimeNodesTree {

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

    private static Map<Long, Boolean> primeCache = new HashMap<>();

    public static void main(String[] args) {
        int N = InputUtils.nextInt();
        int tt = N - 1;

        Tree tree = new Tree();

        while(tt-- > 0) {
            String[] line = InputUtils.splitNextLine();
            tree.edge(Integer.valueOf(line[0]), Long.valueOf(line[1]));
        }
        System.out.println(tree.get().entrySet().stream().filter(e -> isPrime(e.getValue())).count());

    }

    private static boolean isPrime(long n) {
        if (null == primeCache.get(n)) primeCache.put(n, new BigInteger(String.valueOf(n)).isProbablePrime(1));
        return primeCache.get(n);
    }

}


