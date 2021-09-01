package code.arrays;

public class JumpGameI {

    class Solution {
        public boolean canJump(int[] nums) {
            if (nums.length == 1) return true;
            boolean[] canJump = new boolean[nums.length];
            for (int i = nums.length - 2; i > -1; i--) {
                if (i + nums[i] >= nums.length - 1) canJump[i] = true;
                else
                    for (int j = i+1; j <= i + nums[i]; j++) {
                        if (canJump[j] == true) {
                            canJump[i] = true;
                            break;
                        }
                    }
            }
            return canJump[0];
        }
    }

    class Solution2 {
        public boolean canJump(int[] nums) {
            if (nums.length == 1) return true;
            int max = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if(i > max) return false;
                max = Math.max(max, i + nums[i]);
            }
            return max >= nums.length - 1;
        }
    }

    class Solution3 {
        public boolean canJump(int[] nums) {
            int previous = nums.length - 1;
            for (int i = nums.length - 2; i >= 0; i--)
                if (i + nums[i] >= previous)
                    previous = i;
            return previous <= 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new JumpGameI().new Solution().canJump(new int[] { 1, 2, 3 })
        );
    }

}
