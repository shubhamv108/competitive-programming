package code.shubham.arrays;

import java.util.HashMap;

public class LengthOfLongestSubarrayWithAtMostKFrequency {
    class Solution {
        public int maxSubarrayLength(int[] A, int k) {
            int result = 0;
            HashMap<Integer, Integer> f = new HashMap<>();
            for (int l = 0, r = 0; r < A.length; ++r) {

                while (l < r && f.getOrDefault(A[r], 0) == k) {
                    f.put(A[l], f.get(A[l]) - 1);
                    ++l;
                }

                f.put(A[r], f.getOrDefault(A[r], 0) + 1);

                result = Math.max(result, r - l + 1);
            }
            return result;
        }
    }

    class Solution2 {
        public int maxSubarrayLength(int[] A, int k) {
            int result = 0, min = A[0], max = A[0];

            for (int a : A) {
                min = Math.min(a, min);
                max = Math.max(a, max);
            }

            int[] f = new int[max - min + 1];
            for (int l = 0, r = 0; r < A.length; ++r) {
                while (l < r && f[A[r] - min] == k)
                    --f[A[l++] - min];

                ++f[A[r] - min];

                result = Math.max(result, r - l + 1);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubarrayWithAtMostKFrequency().new Solution2().maxSubarrayLength(new int[] {1, 3, 1, 1, 1}, 2));
    }
}
