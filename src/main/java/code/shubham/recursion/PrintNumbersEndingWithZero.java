package code.shubham.recursion;

import java.util.Scanner;

public class PrintNumbersEndingWithZero {
    static Scanner sc = new Scanner(System.in);
    static int N;

    static void printSpecialNumbers() {
        if(N == 0) {
            return;
        }
        String s = sc.next();
        if('0' == s.charAt(s.length() - 1)) {
            System.out.print(s + " ");
        }
        N--;
        printSpecialNumbers();
    }

    public static void main(String[] args)
    {
        int T = Integer.parseInt(sc.next());
        while(T-- > 0) {
            N = Integer.parseInt(sc.next());
            printSpecialNumbers();
            System.out.println();
        }
    }
}
