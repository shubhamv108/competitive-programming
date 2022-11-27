package code.shubham.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class MaximumGap {

    int get(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = nums[0], max = nums[0];
        for (int n : nums) {
            min = n < min ? n : min;
            max = n > max ? n : max;
        }
        int bucketSize = Math.max((max - min) / (nums.length - 1), 1);
        int bucketCount = (max - min) / (bucketSize);
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>(bucketCount);
        IntStream.rangeClosed(0, bucketCount).forEach(i -> buckets.add(new LinkedList<>()));
        for (int n:nums) {
            int bucketNumber = (n - min) / bucketSize;
            buckets.get(bucketNumber).add(n);
        }

        int result = 0, currMax = 0;
        for (LinkedList<Integer> b : buckets) {
            if (b.isEmpty()) continue;
            int prevMax = currMax > 0 ? currMax : b.get(0), currMin = b.get(0);
            for (int n : b) {
                currMax = Math.max(currMax, n);
                currMin = Math.min(currMin, n);
            }
            result = Math.max(result, currMin - prevMax);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new MaximumGap().get(new int[] {3, 6, 9, 1})
        );
    }

}
