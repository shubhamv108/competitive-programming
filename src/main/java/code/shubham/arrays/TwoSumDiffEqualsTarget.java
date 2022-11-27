package code.shubham.arrays;

import java.util.Arrays;

public class TwoSumDiffEqualsTarget {

    public class Solution {
        /**
         * @param nums: an array of Integer
         * @param target: an integer
         * @return: [num1, num2] (num1 < num2)
         */
        public int[] twoSum7(int[] nums, int target) {
            int[] result = new int[] {};
            int l = nums.length;
            for (int i = 0; i < l; i++) {
                if (target > nums[i] && binarySearch(nums, i+1, l, target + nums[i], i)) {
                    result = new int[] { nums[i], target + nums[i] };
                    break;
                } else if (binarySearch(nums, 0, l, nums[i] - target, i)) {
                    result = new int[] { nums[i] - target, nums[i] };
                    break;
                }
            }
            Arrays.sort(result);
            return result;
        }

        boolean binarySearch(int[] nums, int l, int r, int n, int k) {
            int m = 0;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (m != k && nums[m] == n) return true;
                if (nums[m] < n) l = m + 1;
                else r = m - 1;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TwoSumDiffEqualsTarget().new Solution().twoSum7(new int[] {2,7,15,24}, 5)
        );
    }

}
