package code.arrays;

public class MaxAbsoluteDifference {
    public class Solution {
        public int maxArr(int[] A) {
            int addedMax = Integer.MIN_VALUE;
            int addedMin = Integer.MAX_VALUE;
            int subMax = Integer.MIN_VALUE;
            int subMin = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                addedMax = Math.max(addedMax, A[i] + i);
                addedMin = Math.min(addedMin, A[i] + i);
                subMax = Math.max(subMax, A[i] - i);
                subMin = Math.min(subMin, A[i] - i);
            }
            return Math.max(addedMax - addedMin, subMax - subMin);
        }
    }
}
