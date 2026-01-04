package code.shubham;

public class MinimumTimeToMakeRopeColorful {
    class Solution {
        public int minCost(String s, int[] A) {
            int result = 0;
            for (int i = 1; i < A.length; ++i) {
                if (s.charAt(i) == s.charAt(i-1)) {
                    result += Math.min(A[i], A[i-1]);
                    A[i] = Math.max(A[i], A[i-1]);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumTimeToMakeRopeColorful()
                        .new Solution()
                        .minCost(
                                "abaac",
                                new int [] { 1, 2, 3, 4, 5 }));
    }
}
