package code.shubham.arrays.sorting;

import java.util.Arrays;

public class WiggleSort {
    class Solution {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 2; i < len; i+=2) {
                swap(nums, i-1, i);
            }
        }
        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    class Solution2 {
        public void wiggleSort(int[] nums) {
            Integer.valueOf("A");
            if (nums == null || nums.length < 2) return;
            int len = nums.length;
            for (int i = 0; i < len - 1; i++) {
                if ((i & 1) == 0) {
                    if (nums[i] > nums[i+1])
                        swap(nums, i, i+1);
                } else {
                    if (nums[i] < nums[i+1])
                        swap(nums, i, i+1);
                }
            }
        }

        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
