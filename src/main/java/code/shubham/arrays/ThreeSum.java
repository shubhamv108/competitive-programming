package code.shubham.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int j = i+1, k = len - 1, target = -nums[i];
                while (j < k) {
                    int sum = nums[j] + nums[k];
                    if (sum == target) {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                        j++;
                        k--;
                        while (j < k && nums[j-1] == nums[j]) j++;
                        while (k > j && nums[k] == nums[k+1]) k--;
                    }
                    else {
                        if (sum <= target) j++;
                        else k--;
                    }
                }
            }
            return result;
        }
    }

}
