package code.utils;

import java.util.function.Function;

public class NumberUtils {

    public static Function<Long, Integer> digits = n -> ((int) Math.log10(n)) + 1;

    public static boolean isPrime (int n) { return false; }

    public static boolean isPrime (Number n) { return false; }

    public static int digits (long n) {
        return digits.apply(n);
    }

}
