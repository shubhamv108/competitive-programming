package code.dynamicprogramming;

import java.util.Arrays;

public class MaxSumSubsequence {

    class Solution {
        int[] A;
        Solution(int[] A) {
            this.A = A;
        }
        int solve() {
           Arrays.sort(A);
           for (int i = A.length; i > -1; i--) {
               long curSum  = 0;
               for (int j = 0)
               if (curSum )
           }
        }
    }

    public static void main(String[] args) {
        int[] A = input.InputUtils.nextIntLine();
        System.out.println(
                new MaxSumSubsequence().new Solution(A).solve()
        );
    }

}
