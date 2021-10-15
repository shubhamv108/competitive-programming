package code.arrays;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK {
    public class Solution {
        public int maxSubArrayLen(int[] nums, int k) {
            HashMap<Integer, Integer> m = new HashMap<>();
            int sum = 0, maxSum = 0, result = 0, len = nums.length, size = 0;
            for (int i = 0; i < len; i++) {
                sum += nums[i];
                Integer j = m.get(sum - k);
                if (sum == k) {
                    size = i + 1;
                } else if (j != null) {
                    size =  i - j;
                }
                if (size > result) {
                    result = size;
                }
                if (!m.containsKey(sum)) m.put(sum, i);
            }
            return result;
        }
    }
}
