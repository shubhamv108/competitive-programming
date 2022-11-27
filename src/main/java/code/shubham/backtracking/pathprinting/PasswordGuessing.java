package code.shubham.backtracking.pathprinting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordGuessing {

    static boolean isAlpha(char ch) {
        int a = (int) ch;
        return (a > 64 && a < 91) || (a > 96 && a < 123);
    }

    static boolean isUpperCase(char ch) {
        int a = (int) ch;
        return (a > 64 && a < 91);
    }

    static boolean isLowerCase(char ch) {
        int a = (int) ch;
        return (a > 96 && a < 123);
    }

    static void printPasswordsHelper(String x, int xi, int n, char[] p, int pi) {
        if(pi == n) {
            for (char c : p) System.out.printf("%c", c);
            System.out.printf(" ");
            return;
        }
        char ch = x.charAt(xi);
        int a = (int) ch;
        if(isUpperCase(ch)) {
            p[pi] = (char) (a + 32);
            printPasswordsHelper(x, xi + 1, n, p, pi + 1);
        }
        p[pi] = (char) a;
        printPasswordsHelper(x, xi + 1, n, p, pi + 1);
        if(isLowerCase(ch)) {
            p[pi] = (char) (a - 32);
            printPasswordsHelper(x, xi + 1, n, p, pi + 1);
        }
    }

    static void printPasswords(String s, int n) {
        char[] p = new char[n];
        printPasswordsHelper(s, 0, n, p, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            String s = br.readLine();
            printPasswords(s, s.length());
        }

    }
}
