package code.arrays;

/**
 * Input: nums = [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 */
public class PartitionArrayIntoDisjointInterval {

    public int partitionDisjoint(int[] nums) {
        int leftMax = nums[0], partitionIdx = 0, max = leftMax;
        for (int i = 1; i < nums.length; i++)
            if (leftMax > nums[i]) {
                leftMax = max;
                partitionIdx = i;
            } else {
                max = Math.max(max, nums[i]);
            }
        return partitionIdx + 1;
    }

}
