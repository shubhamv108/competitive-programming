package code.arrays.sorting;

import java.util.Arrays;

public class WiggleSort2 {
    public class Solution {
        public void wiggleSort(int[] nums) {
            int[] temp = nums.clone();
            Arrays.sort(temp);
            int n = nums.length;
            int left = (n-1)/2;
            int right = n-1;

            int index = 0;
            while (left >= 0 && right >= (n+1)/2) {
                nums[index++] = temp[left--];
                nums[index++] = temp[right--];
            }
            if (left >= 0) {
                nums[index] = temp[left];
            }
        }
    }
}
