package code.shubham.goemetry;

import java.util.Arrays;

public class ValidTriangleNumbers {

    class Solution {
        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int count = 0, n = nums.length;
            for (int i = n-1; i >= 2; i--) {
                int l = 0, r = i - 1;
                while (l < r) {
                    if (nums[l] + nums[r] > nums[i]) {
                        count += r-l;
                        r--;
                    }
                    else l++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ValidTriangleNumbers().new Solution().triangleNumber(new int[]{2, 2, 3, 4})
        );
    }

}
