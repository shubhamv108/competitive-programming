package code.dynamicprogramming;

public class MaximalLengthOfRepeaedSubarray {
    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int result = 0;
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            for (int i = nums1.length - 1; i > -1; i--) {
                for (int j = nums2.length - 1; j > -1; j--) {
                    if (nums1[i] == nums2[j])
                        dp[i][j] = dp[i + 1][j + 1] + 1;

                    result = Math.max(result, dp[i][j]);
                }
            }
            return result;
        }
    }

    class Solution2 {
        public int findLength(int[] nums1, int[] nums2) {
            int result = 0, dp[] = new int[nums2.length + 1], i, j, prev, current;
            for (i = nums1.length - 1; i > -1; i--) {
                prev = 0;
                for (j = nums2.length - 1; j > -1; j--) {
                    current = dp[j];

                    if (nums1[i] == nums2[j]) {
                        dp[j] = prev + 1;
                        result = Math.max(result, dp[j]);
                    } else {
                        dp[j] = 0;
                    }

                    prev = current;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaximalLengthOfRepeaedSubarray().new Solution().findLength(
                        new int[] { 1,2,3,2,1 }, new int[] { 3,2,1,4,7 }
                )
        );
    }
}
