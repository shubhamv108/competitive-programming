package code.shubham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    class Solution {
        public List<Integer> largestDivisibleSubset(int[] A) {
            Arrays.sort(A);

            int[] dp = new int[A.length];
            int[] prev = new int[A.length];
            dp[0] = 1;
            prev[0] = -1;
            int k = 0;
            for (int i = 1; i < A.length; ++i) {
                dp[i] = 1;
                prev[i] = -1;
                for (int j = 0; j < i; ++j) {
                    if (A[i] % A[j] == 0) {
                        if (1 + dp[j] > dp[i]) {
                            dp[i] = 1 + dp[j];
                            prev[i] = j;
                        }
                    }
                }
                if (dp[k] < dp[i])
                    k = i;
            }

            ArrayList<Integer> result = new ArrayList<>();
            while (k != -1) {
                result.add(A[k]);
                k = prev[k];
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LargestDivisibleSubset().new Solution().largestDivisibleSubset(new int[] { 1,2,3 }));
    }
}
