package code.arrays;

public class MaxLengthOfSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        int negStart = -1, negCount = 0, zeroStart = -1, result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negCount++;
                if (negStart == -1) negStart = i;
            }

            if (nums[i] == 0) {
                zeroStart = i;
                negCount = 0;
                negStart = -1;
            } else {
                if ((negCount & 1) == 0) result = Math.max(result, i - zeroStart);
                else result = Math.max(result, i - negStart);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxLengthOfSubarrayWithPositiveProduct().getMaxLen(new int[] { 1,-2,0, 0, -3,4 })
        );
    }
}
