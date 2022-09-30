package code.arrays;

public class ShortestSubArrayToBeRemovedToMakearraySorted {
    class Solution {
        public int findLengthOfShortestSubarray(int[] A) {
            int i, len = A.length, prev = 0, start = 0, end = len - 1;
            for (i = 1; i < len; i++) {
                if (A[i] < prev)
                    start = i - 1;
                prev = A[i];
            }

            prev = A[len - 1];
            for (i = len - 2; i > -1; i--) {
                if (A[i] > prev)
                    end = i + 1;
                prev = A[i];
            }

            int max = A[start], min = A[end];
            for (i = start + 1; i <= end; i++) {
                max = Math.max(max, A[i]);
                min = Math.min(min, A[i]);
            }

            for (i = 0; i < start; i++)
                if (A[i] > min) {
                    start = i;
                    break;
                }

            for (i = len - 1; i > end; i--)
                if (A[i] < max) {
                    end = i;
                    break;
                }

            return end - start + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ShortestSubArrayToBeRemovedToMakearraySorted().
                        new Solution().
                            findLengthOfShortestSubarray(new int[] { 1, 2, 3, 10, 4, 2, 3, 5 })

        );
    }
}
