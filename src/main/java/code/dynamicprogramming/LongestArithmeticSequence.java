package code.dynamicprogramming;

import java.util.HashMap;

public class LongestArithmeticSequence {

    class Solution {
        public int longestArithSeqLength(int[] A) {
            int res = 2, n = A.length;
            HashMap<Integer, Integer>[] dp = new HashMap[n];
            for (int j = 0; j < A.length; j++) {
                dp[j] = new HashMap<>();
                for (int i = 0; i < j; i++) {
                    int d = A[j] - A[i];
                    dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                    res = Math.max(res, dp[j].get(d));
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new LongestArithmeticSequence().new Solution().longestArithSeqLength(new int[] { 3,6,9,12 })
        );
    }

}
