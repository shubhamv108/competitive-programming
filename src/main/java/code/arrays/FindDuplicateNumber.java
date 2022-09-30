package code.arrays;

public class FindDuplicateNumber {

    // assgining negative value to visited
    class Solution {
        public int findDuplicate(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[Math.abs(nums[i]) - 1] < 0)
                    return Math.abs(nums[i]);
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
            return -1;
        }
    }

    // slow, fast pointers
    class Solution2 {
        public int findDuplicate(int[] A) {
            int slow = A[0];
            int fast = A[A[0]];

            while (fast != slow) {
                slow = A[slow];
                fast = A[A[fast]];
            }
            slow = 0;
            while (fast != slow) {
                slow = A[slow];
                fast = A[fast];
            }
            return slow;
        }
    }

}
