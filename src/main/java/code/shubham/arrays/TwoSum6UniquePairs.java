package code.shubham.arrays;

import java.util.HashMap;

public class TwoSum6UniquePairs {
    public class Solution {
        public int twoSum6(int[] nums, int target) {
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);

            int result = 0;
            for ( int key : count.keySet() ) {
                int req = target - key;
                if ( (req == key && count.get(req) > 1 )
                        || ( req != key && count.containsKey(req) && key < req)) {
                    result++;
                }
            }
            return result;
        }
    }
}
