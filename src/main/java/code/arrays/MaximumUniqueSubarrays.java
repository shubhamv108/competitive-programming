package code.arrays;

import java.util.HashSet;
import java.util.Set;

public class MaximumUniqueSubarrays {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> unique = new HashSet<>();

        int i = 0, j = 0;
        int sum = 0;
        while (i < nums.length && j < nums.length) {
            if (!unique.contains(nums[j])) {
                unique.add(nums[j]);
                sum += nums[j];
                j++;
            } else {
                while (nums[i] != nums[j]) {
                    unique.remove(nums[i]);
                    sum -= nums[i];
                    i++;
                }
                unique.remove(nums[i]);
                sum -= nums[i];
                i++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(
        new MaximumUniqueSubarrays().maximumUniqueSubarray(new int[] { 5,2,1,2,5,2,1,2,5 }));
    }
}
