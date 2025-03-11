package code.shubham.topk;

import java.util.ArrayList;

public class TopKFrequentElements {
    class Solution {
        // Time: O(n), Space: O(n)
        public int[] topKFrequent(int[] A, int k) {
            int[] result = new int[k];
            int ri = 0;

            int min = A[0], max = A[0];
            for (int a : A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int[] f = new int[max - min + 1];
            for (int a : A)
                ++f[a - min];

            ArrayList<Integer>[] countBuckets = new ArrayList[A.length + 1];
            for (int n = 0; n < f.length; ++n) {
                if (f[n] > 0) {
                    if (countBuckets[f[n]] == null)
                        countBuckets[f[n]] = new ArrayList<>();
                    countBuckets[f[n]].add(n);
                }
            }

            for (int i = countBuckets.length - 1; i > -1; --i)
                if (countBuckets[i] != null)
                    for (int j = 0; j < countBuckets[i].size(); ++j) {
                        result[ri++] = countBuckets[i].get(j) + min;
                        if (ri == k)
                            return result;
                    }

            return result;
        }
    }
}
