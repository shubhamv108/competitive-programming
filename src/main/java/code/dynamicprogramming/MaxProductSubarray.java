package code.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class MaxProductSubarray {

    public class Solution {
        public int maxProduct(final List<Integer> A) {
            int minVal     = A.get(0);
            int maxVal     = A.get(0);
            int maxProduct = A.get(0);
            int temp;
            for (int i = 1; i < A.size(); i++) {
                if (A.get(i) < 0) {
                    temp = maxVal;
                    maxVal = minVal;
                    minVal = temp;
                }
                maxVal     = Math.max(A.get(i), maxVal * A.get(i));
                minVal     = Math.min(A.get(i), minVal * A.get(i));
                maxProduct = Math.max(maxProduct, maxVal);
            }
            return maxProduct;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxProductSubarray().new Solution().maxProduct(Arrays.asList(1, -2, -3, 0, 7, -8, -2)));
    }

}
