package code.arrays;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, count  = 0;
        HashMap<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            Integer c = sums.get(sum - k);
            if (c != null) {
                count += c;
            }

            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[] { 3, 2, 3, 2 }, 5 ));
    }

}