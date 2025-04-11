package code.shubham._01Knapsack.LongestCommonSubstring;

public class LongestAlternatingSubsequence {

    class Solution {
        int alternatingMaxLength(int[] A) {
            int inc = 1, dec = 1;

            for (int i = 1; i < A.length; ++i) {
                if (A[i-1] < A[i])
                    inc = dec + 1;
                else if (A[i-1] > A[i])
                    dec = inc + 1;
            }

            return Math.max(inc, dec);
        }
    }

}
