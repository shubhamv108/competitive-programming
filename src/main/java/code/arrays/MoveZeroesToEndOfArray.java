package code.arrays;

public class MoveZeroesToEndOfArray {

    class Solution {
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length < 2) return;
            int len = nums.length;
            int p = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] != 0) {
                    nums[p] = nums[i];
                    if (i != p) nums[i] = 0;
                    p++;
                }
            }
        }
    }

}
