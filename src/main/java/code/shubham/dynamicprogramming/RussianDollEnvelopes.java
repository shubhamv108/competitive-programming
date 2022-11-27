package code.shubham.dynamicprogramming;

import java.util.Arrays;

public class RussianDollEnvelopes {
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
            int len = envelopes.length, dp[] = new int[len], i, j, result = 1;
            dp[0] = 1;
            for (i = 1; i < len; i++) {
                dp[i] = 1;
                for (j = 0; j < i; j++)
                    if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                        dp[i] = Math.max(dp[i], dp[j] + 1);

                result = Math.max(dp[i], result);
            }
            return result;
        }
    }
    class Solution2 {
        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes == null || envelopes.length == 0 ||
                    envelopes[0] == null || envelopes[0].length != 2)
                return 0;

            Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
            int dp[] = new int[envelopes.length];
            int len = 0, index;
            for(int[] envelope : envelopes){
                index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if (index < 0)
                    index = -(index + 1);
                dp[index] = envelope[1];
                if (index == len)
                    len++;
            }
            return len;
        }
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(
            new RussianDollEnvelopes().new Solution2().maxEnvelopes(envelopes)
        );
    }
}
