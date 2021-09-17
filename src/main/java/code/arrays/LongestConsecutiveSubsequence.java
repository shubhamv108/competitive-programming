package code.arrays;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSubsequence {

    class Solution {
        public int longestConsecutive(int[] nums) {
            int result = 0;
            Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
            for (int n : set) {
                if (!set.contains(n-1)) {
                    int cur = n, curResult = 1;
                    while (set.contains(++cur)) curResult++;
                    result = Math.max(curResult, result);
                }
            }
            return result;
        }
    }

}
