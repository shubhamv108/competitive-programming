package code.shubham.topk;

import java.util.HashMap;
import java.util.Map;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    class Solution {
        public int findLeastNumOfUniqueInts(int[] A, int k) {
            Map<Integer, Integer> f = new HashMap<>();
            for (int a : A)
                f.merge(a, 1, Integer::sum);


            int[] b = new int[A.length + 1];
            for (int v : f.values())
                ++b[v];

            int result = f.size();
            for (int i = 0; i < b.length && k > 0; ++i) {
                while (b[i] > 0 && k >= i) {
                    k -= i;
                    --b[i];
                    --result;
                }
            }

            return result;
        }
    }
}
