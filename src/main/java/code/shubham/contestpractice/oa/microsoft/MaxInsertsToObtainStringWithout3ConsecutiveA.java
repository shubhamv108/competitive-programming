package code.shubham.contestpractice.oa.microsoft;

import java.util.Scanner;

import static code.shubham.contestpractice.oa.microsoft.MaxInsertsToObtainStringWithout3ConsecutiveA.Solution.maxInserts;

public class MaxInsertsToObtainStringWithout3ConsecutiveA {
    class Solution {
        public static int maxInserts(String s) {
            int countA = 0;
            int countOthers = 0;
            int len = s.length();
            for (int i = 0; i < len; ++i) {
                if (s.charAt(i) == 'a') {
                    ++countA;
                } else {
                    ++countOthers;
                    countA = 0;
                }

                if (countA == 3)
                    return -1;
            }
            return 2 * (countOthers + 1) - (len - countOthers);
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        int res = maxInserts(s);
        System.out.println(res);
    }
}
