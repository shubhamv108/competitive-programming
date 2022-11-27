package code.shubham.backtracking;

public class MaximumSumPathInTwoArrays {

    class Solution {
        public int maxSum(int[] nums1, int[] nums2) {
            long curSum = 0, sum1 = 0, sum2 = 0;
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    curSum += Math.max(sum1, sum2) + nums1[i];
                    sum1 = 0;
                    sum2 = 0;
                    i++; j++;
                } else if (nums1[i] < nums2[j]) {
                    sum1 += nums1[i++];
                } else {
                    sum2 += nums2[j++];
                }
            }
            while (i < nums1.length) {
                sum1 += nums1[i++];
            }
            while (j < nums2.length) {
                sum2 += nums2[j++];
            }
            curSum += Math.max(sum1, sum2);
            return (int) (curSum % (1e9+7));
        }
    }

    public static void main(String[] args) {
        int ar1[] = { 2, 3, 7, 10, 12, 15, 30, 34 };
        int ar2[] = { 1, 5, 7, 8, 10, 15, 16, 19 };
        System.out.println(new MaximumSumPathInTwoArrays().new Solution().maxSum(ar1, ar2));
    }

}
