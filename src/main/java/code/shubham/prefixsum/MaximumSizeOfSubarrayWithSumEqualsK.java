package code.shubham.prefixsum;

import java.util.HashMap;

public class MaximumSizeOfSubarrayWithSumEqualsK {
    class Solution {
        public int maxSubArrayLen(int[] A, int k) {
            HashMap<Integer, Integer> m = new HashMap<>();
            int sum = 0, result = 0;
            m.put(0, -1);
            for (int i = 0; i < A.length; ++i) {
                sum += A[i];
                Integer p = m.get(sum - k);
                if (m.get(sum) == null)
                    m.put(sum, i);
                if (p == null)
                    continue;
                result = Math.max(result, i - p);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSizeOfSubarrayWithSumEqualsK().new Solution().maxSubArrayLen(new int[] { 1,1,0 }, 1));
    }
}
