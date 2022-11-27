package code.shubham.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationsII {
    class Solution {
        HashSet<List<Integer>> set = new HashSet<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            permutations(nums, 0);
            return new ArrayList<>(set);
        }

        void permutations(int[] nums, int index) {
            if (nums.length - 1 == index) {
                set.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }
            for (int i = index; i < nums.length; i++) {
                swap(nums, i, index);
                permutations(nums, index + 1);
                swap(nums, i, index);
            }
        }

        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
