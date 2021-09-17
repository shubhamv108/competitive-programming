package code.arrays;

public class ContainerWithMostWater {

    class Solution {
        public int maxArea(int[] A) {
            int l = 0, r = A.length - 1, result = Math.min(A[l], A[r]) * (r-l);
            while (l < r) {
                while (l < A.length - 1 && A[l] <= A[r]) {
                    l++;
                    int area = Math.min(A[l], A[r]) * (r - l);
                    result = Math.max(result, area);
                }

                while (r > 0 && A[r] <= A[l]) {
                    r--;
                    int area = Math.min(A[l], A[r]) * (r - l);
                    result = Math.max(result, area);
                }
            }
            return result;
        }
    }

}
