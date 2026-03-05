package code.shubham.slidingwindow;

import java.util.HashMap;

public class CountSubArraysWithKDistinctIntegers {
    class Solution {
        public long countSubarrays(int[] A, int k, int m) {
            long result = 0;
            int n = A.length;

            HashMap<Integer, Integer> f = new HashMap<>();

            int start = 0;
            int v = 0;
            int distinct = 0;
            int good = 0;

            for (int end = 0; end < n; ++end) {
                int c = f.getOrDefault(A[end], 0) + 1;
                f.put(A[end], c);

                if (c == 1)
                    ++distinct;
                if (c == m)
                    ++good;

                while (distinct > k) {
                    if (f.get(A[start]) == m)
                        --good;

                    int sc = f.get(A[start]) - 1;
                    f.put(A[start], sc);

                    if (sc == 0)
                        --distinct;

                    ++start;
                    v = start;
                }

                for (; distinct == k && good == k && f.get(A[v]) > m;) {
                    f.put(A[v], f.get(A[v]) - 1);
                    ++v;
                }

                if (distinct == k && good == k)
                    result += (v - start + 1);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountSubArraysWithKDistinctIntegers().new Solution().countSubarrays(new int[] {1,2,1,2,2}, 2, 2));
        System.out.println(new CountSubArraysWithKDistinctIntegers().new Solution().countSubarrays(new int[] {3,1,2,4}, 2, 1));
    }
}
