package code.backtracking;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            permutations(nums, 0);
            return result;
        }

        void permutations(int[] nums, int index) {
            if (nums.length - 1 == index) {
                result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
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

    public static void main(String[] args) {
        System.out.println(
                new Permutations().new Solution().permute(new int[] { 1, 2, 3 })
        );
    }

}
