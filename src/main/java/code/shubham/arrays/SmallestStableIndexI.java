package code.shubham.arrays;

public class SmallestStableIndexI {
        class Solution {
            public int firstStableIndex(int[] A, int k) {
                int n = A.length;
                int[] max = new int[n];
                max[0] = A[0];
                for (int i = 1; i < n; ++i)
                    max[i] = Math.max(max[i-1], A[i]);

                int min = max[n-1] + 1, result = -1;
                for (int i = n - 1; i >= 0; --i) {
                    min = Math.min(min, A[i]);
                    if (max[i] - min <= k)
                        result = i;
                }
                return result;
            }
        }

    void main() {
        System.out.println(new SmallestStableIndexI().new Solution().firstStableIndex(
            new int[] { 3, 2, 1 }, 1
        ));
    }
}
