package code.bitset;

import java.util.*;

public class StreamOfIntegersCheckIfIntegerIsPresent {

    private static BitSet set = new BitSet();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] s = sc.nextLine().split(" ");
            int n = Integer.valueOf(s[1]);
            if (s[0].charAt(0) == 'a')
                add(n);
            else
                System.out.println(check(n));
        }
    }

    private static boolean check(int n) {
        return set.get(n);
    }

    private static void add(Integer n) {
        set.set(n);
    }

}
