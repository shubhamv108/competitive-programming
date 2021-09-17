package code.arrays.sorting;

import java.util.Arrays;

public class SortColors {

    class Solution {
        public void sortColors(int[] nums) {
            if (nums == null) return;
            int len = nums.length;
            if (len < 2) return;

            // Dutch National Flag Sort
            int l = 0, m = 0, r = len - 1;
            while (m <= r) {
                if (nums[m] == 0) {
                    nums[m] = nums[l];
                    nums[l] = 0;
                    l++;
                }
                if (nums[m] == 2) {
                    nums[m] = nums[r];
                    nums[r] = 2;
                    r--;
                    m--;
                }
                m++;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {0, 1, 2};
        new SortColors().new Solution().sortColors(input);
        Arrays.stream(input).forEach(e -> System.out.print(e + " "));
    }

}
