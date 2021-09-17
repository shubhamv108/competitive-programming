

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

class SHUFFLIN {
    private class Solution {

        private Solution() {

        }

        private long solve(long N, long[] A) {
            int i = 0, j = 1;
            while (i < N && j < N) {
                while (i < N && BitwiseUtils.isEven(A[i])) i += 2;
                while (j < N && BitwiseUtils.isOdd(A[j])) j += 2;
                if (i < N && j < N) {
                    ArrayUtils.swap(A, i, j);
                }
            }
            long result = 0;
            for (int k = 0; k < N; k++) {
                result += BitwiseUtils.isOdd(A[k]+1+k) ? 1 : 0;
            }
            return result;
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
    private static class OutputUtils {
        public static void print(Object o) {
            System.out.print(o);
        }
        public static void println(Object o) {
            System.out.println(o);
        }
        public static void print(int[] A) { ArrayUtils.print(A); }
    }
    private static void print(Object o) {
        OutputUtils.print(o);
    }
    private static void println(Object o) {
        OutputUtils.println(o);
    }
    private static void print(int[] A) {
        OutputUtils.print(A);
    }
    private static class MathUtils {
        public static int digits (long n) {
            return ((int) Math.log10(n)) + 1;
        }
        public static int digits (int n) {
            return ((int) Math.log10(n)) + 1;
        }
        public static int gcd(int A, int B) {
            return B == 0 ? 1 : gcd(B, A % B);
        }
        public static int gcd(int[] N) {
            if (N == null || N.length == 0) return -1;
            int gcd = N[0];
            for (int n : N) {
                gcd = gcd(gcd, n);
                if (1 == gcd) return 1;
            }
            return gcd;
        }
        public static boolean[] SOE(int n) {
            boolean[] isPrime = new boolean[n+1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            double sqrt = Math.sqrt(n);
            for (int i = 2; i < sqrt; i++)
                if (isPrime[i])
                    for (int j = i*i; j < n; j+=i)
                        isPrime[j] = false;
            return isPrime;
        }
        public static long factorial(int n) {
            boolean[] isPrime = SOE(n);
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    int k = n, power = 0;
                    while(k > 1) {
                        k /= i;
                        power += k;
                    }
                    factorial *= Math.pow(i, powerOfPrimeForFactorial(n, i));
                }
            }
            return factorial;
        }
        private static  int powerOfPrimeForFactorial(int n, int prime) {
            int t = n / prime;
            if (t == 0) return 0;
            return t + powerOfPrimeForFactorial(t, prime);
        }
        public static double power(double x, int n) {
            if (x == 1 || n == 0) return 1;
            if (n == 1) return x;
            if (n == 0) return 0;
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            return ((n & 1) == 0) ? power(x * x, n / 2) : x * power(x * x, n / 2);
        }
        public static long power(long x, int n) {
            if (x == 1 || n == 0) return 1;
            if (n == 1) return x;
            if (n == 0) return 0;
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            return ((n & 1) == 0) ? power(x * x, n / 2) : x * power(x * x, n / 2);
        }
    }
    private static class GeometryUtils {
        private static class Point {
            private int x;
            private int y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        public static double euclideanDistance(int x1, int y1, int x2, int y2) {
            return Math.sqrt(
                        Math.pow((x1 - x2), 2)
                        +
                        Math.pow((y1 - y2), 2));
        }
        public static int manhattanDistance(int x1, int y1, int x2, int y2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }
    }
    private static class BitwiseUtils {
        public static int NOT ( int n ) {
            return ~n;
        }
        public static int AND ( int a, int b ) {
            return a & b;
        }
        public static int OR(int a, int b) {
            return a | b;
        }
        public static int XOR(int a, int b) {
            return a ^ b;
        }
        public static int twosComplement(int n) {
            return onesComplement(n) + 1;
        }
        public static boolean isOdd ( int n ) {
            return ( n & 1 ) == 1;
        }
        public static boolean isOdd ( long n ) {
            return ( n & 1 ) == 1;
        }
        public static boolean isEven ( int n ) {
            return ( n & 1 ) == 0;
        }
        public static boolean isEven ( long n ) {
            return ( n & 1 ) == 0;
        }
        public static int leftShift ( int n ) {
            return leftShiftByK(n, 1);
        }
        public static int rightShift ( int n ) {
            return rightShiftByK(n, 1);
        }
        public static int leftShiftByK ( int n, int k ) {
            return n << k;
        }
        public static int rightShiftByK ( int n, int k ) {
            return n >> k;
        }
        public static int unsigndRightShift ( int n ) {
            return unsignedRightShiftByK ( n, 1 );
        }
        public static int unsignedRightShiftByK ( int n, int k ) {
            return n >>> k;
        }
        public static boolean isPowerOfTwo ( int n ) {
            return n != 0 && ( ( n & ( n - 1 ) ) == 0);
        }
        public static int countOfSetBits ( int n ) {
            int count = 0;
            while( n != 0 ) {
                n &= (n - 1);
                count++;
            }
            return count;
        }
        public static boolean isBitSet ( int n, int ithBit ) {
            return ( n & ( 1 << ithBit ) ) > 0;
        }
        public static int setIthBit ( int n, int ithBit ) {
            return n | (1 << ithBit);
        }
        public static int unsetIthBit ( int n, int ithBit ) {
            return n & ~(1 << ithBit);
        }
        public static int toggleIthBit(int n, int ithBit) {
            return n ^ (1 << ithBit);
        }
        public int getBit(int n, int ithBit) {
            return ((n >> ithBit) & 1);
        }
        public static int rightMostOneNumber ( int n) {
            return n & twosComplement(n);
        }
        public static long setAllRightBits ( long N ) {
            N = N | ( N >> 1 );
            N = N | ( N >> 2 );
            N = N | ( N >> 4 );
            N = N | ( N >> 8 );

            boolean[] b = new boolean[11];
            ArrayList<Integer> a = IntStream.range(0, 11)
                    .filter(i -> b[i] == true)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            return N;
        }
        public static long largestPowerOf2LesserThan(long N) {
            setAllRightBits(N);
            return ( N + 1 ) >> 1;
        }
        private static boolean isZero(String s) {
            return "0".equals(s);
        }
        private static boolean isOne(String s) {
            return "1".equals(s);
        }
        private static boolean isZero(int b) { return 0 == b; }
        private static boolean isOne(int b) {
            return 1 == b;
        }
        public static int numberOfBits(int n) {
            return (int) (Math.floor(Math.log(n) / Math.log(2))) + 1;
        }
        public static int onesComplement(int n) {
            return ((1 << numberOfBits(n)) - 1) ^ n;
        }
    }
    private static class CharUtils {
        public static boolean isAlpha(char ch) {
            int a = (int) ch;
            return (a > 64 && a < 91) || (a > 96 && a < 123);
        }
        public static boolean isUpperCase(char ch) {
            int a = (int) ch;
            return (a > 64 && a < 91);
        }
        public static boolean isLowerCase(char ch) {
            int a = (int) ch;
            return (a > 96 && a < 123);
        }
        private static boolean isZero(char c) {
            int a = (int) c;
            return a == 48;
        }
        private static boolean isOne(char c) {
            int a = (int) c;
            return a == 49;
        }
    }
    private static class ArrayUtils {
        public static void swap(int[] A, int x, int y) {
            int t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(long[] A, int x, int y) {
            long t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(double[] A, int x, int y) {
            double t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(Integer[] A, int x, int y) {
            Integer t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(Long[] A, int x, int y) {
            Long t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(Double[] A, int x, int y) {
            Double t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(char[] A, int x, int y) {
            char t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(Character[] A, int x, int y) {
            Character t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static void swap(String[] A, int x, int y) {
            String t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
        public static int[] toInt(String[] A) {
            return Arrays.stream(A).mapToInt(Integer::valueOf).toArray();
        }
        public static Integer[] toInteger(String[] A) {
            return Arrays.stream(A).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        }
        public static void print(int[] A) {
            Arrays.stream(A).forEach(e -> System.out.println(e + " "));
        }
    }

    public static void main(String[] args) {
        try {
            int t = InputUtils.nextInt();
            SHUFFLIN object = new SHUFFLIN();
            while (t-- > 0) {
                int N = InputUtils.nextInt();
                long[] A = InputUtils.nextLinelongArray();
                println(object.new Solution().solve(N, A));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
