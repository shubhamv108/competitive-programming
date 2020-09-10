package code.dynamicprogramming;

public class HouseRobbery {
    int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int curMax = nums[0];
        int prevMax = 0;
        int temp = 0;
        for (int i = 1; i < nums.length; i++) {
            temp = curMax;
            curMax = Math.max(curMax, nums[i] + prevMax);
            prevMax = temp;
        }
        return curMax;
    }
};