package code.utils;

public class CharUtils {

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
