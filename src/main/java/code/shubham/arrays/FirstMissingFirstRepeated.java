package code.shubham.arrays;

public class FirstMissingFirstRepeated {

    public class Solution {
        // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
        public int[] repeatedNumber(final int[] nums) {
            int[] result = new int[] {-1, -1};
            for (int i = 0; i < nums.length; i++) {
                int n = Math.abs(nums[i]);
                if (nums[n - 1] < 0)
                    result[0] = n;
                else
                    nums[n - 1] *= -1;
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    result[1] = i+1;
                    break;
                }
            }
            return result;
        }
    }


}
