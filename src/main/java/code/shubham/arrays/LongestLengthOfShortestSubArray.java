package code.shubham.arrays;

public class LongestLengthOfShortestSubArray {
    class Solution {
        public int findLengthOfShortestSubarray(int[] A) {
            int i, len = A.length, prev = A[0];

            int r;
            for (r = len - 1; r >= 1; r--)
                if (A[r - 1] > A[r])
                    break;

            int result = r;
            for (i = 0; i < len - 1 && i < r; i++) {
                if (r == len || A[i] <= A[r])
                    result = Math.min(result, r - i - 1);
                else
                    r++;

                if (A[i + 1] < A[i])
                    break;
            }

            return result;
        }
    }

    public static void main(String[] args) {

    }
}
