package code.shubham.utils;

import java.util.function.Function;

public class NumberUtils {

    public static Function<Long, Integer> digits = n -> ((int) Math.log10(n)) + 1;

    public static boolean isPrime (int n) { throw new UnsupportedOperationException(); }

    public static boolean isPrime (Number n) { throw new UnsupportedOperationException(); }

    public static int digits (long n) {
        return digits.apply(n);
    }

    public static int convertBaseToDecimal(int n, int base) {
        int result = 0;
        int digits = 0;
        while (n > 0) {
            int r = n % 10;
            result += r * Math.pow(base, digits++);
            n /= 10;
        }
        return result;
    }

    public static int convertDecimalToBase(int n, int toBase) {
        int result = 0;
        int digits = 0;
        while (n > 0) {
            int r = n % toBase;
            result = (r * (int) Math.pow(10, digits)) + result;
            digits++;
            n /= toBase;
        }
        return result;
    }

}
