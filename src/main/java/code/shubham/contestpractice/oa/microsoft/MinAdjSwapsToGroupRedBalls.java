package code.shubham.contestpractice.oa.microsoft;

import java.util.Scanner;

import static code.shubham.contestpractice.oa.microsoft.MinAdjSwapsToGroupRedBalls.Solution.minSwaps;

public class MinAdjSwapsToGroupRedBalls {
    class Solution {
        public static int minSwaps(String S) {
            char[] s = S.toCharArray();

            int redCount = 0;
            for (int i = 0; i < S.length(); ++i)
                if (s[i] == 'R')
                    ++redCount;

            int l = 0, r = s.length - 1, result = 0;
            while (l < r) {
                if (s[l] == 'R' && s[r] == 'R') {
                    redCount -= 2;
                    result += r - l - 1 - redCount;

                    ++l;
                    --r;
                } else if (s[l] != 'R')
                    ++l;
                else
                    --r;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        int res = minSwaps(s);
        System.out.println(res);
    }

}
