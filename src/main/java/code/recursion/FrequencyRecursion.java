package code.recursion;

import java.util.Scanner;

public class FrequencyRecursion
{
    static Scanner sc = new Scanner(System.in);
    static String[] arr;
    static int N = 0, Q = 0, c;
    static String n;
    static int calcullateFrequency(int idx) {
        if(idx == N) {
            return c;
        }
        if(arr[idx].equals(n)) {
            c++;
        }
        return calcullateFrequency(++idx);
    }
    public static void main(String[] args) {
        int T = Integer.parseInt(sc.next());
        while(T-- > 0) {
            N = Integer.parseInt(sc.next());
            sc.nextLine();
            arr = sc.nextLine().split(" ");
            Q = Integer.parseInt(sc.next());
            while(Q-- > 0) {
                c = 0;
                n = sc.next();
                calcullateFrequency(0);
                System.out.print(c + " ");
            }
        }
    }
}