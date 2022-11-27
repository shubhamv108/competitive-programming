package code.shubham.dynamicprogramming;

public class HouseRobberyII {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        int[] dp1 = new int[nums.length + 1];
        dp[0] = 0;
        dp1[1] = 0;
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i == 0) {
                dp[i + 1] = Math.max(dp[i], nums[i]);
            } else {
                dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp1[i + 1] = Math.max(dp1[i], nums[i]);
            } else {
                dp1[i + 1] = Math.max(dp1[i], dp1[i - 1] + nums[i]);
            }
        }

        return Math.max(dp[nums.length - 1], dp1[nums.length]);
    }
}
