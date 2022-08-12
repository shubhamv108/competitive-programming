package code.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    class Solution {
        public int lengthOfLIS(int[] nums) {
            int result = 0, len = nums.length;
            int[] dp = new int[len];

            for (int i = 0; i < len; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++)
                    if (nums[i] > nums[j])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                result = Math.max(result, dp[i]);
            }

            return result;
        }
    }

    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int len = 0, dp[] = new int[nums.length];

            for(int n : nums) {
                int i = Arrays.binarySearch(dp, 0, len, n);
                if (i < 0)
                    i = -(i + 1);
                dp[i] = n;
                if(i == len)
                    len++;
            }

            return len;
        }
    }

    public static void main(String[] args) {
        int[] a = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(
                new LongestIncreasingSubsequence().new Solution2().lengthOfLIS(a)
        );
    }

}
