package code.stacksqueues;

import java.util.List;

public class RainWaterTrapped {
    class Solution {
        public int trap(final List<Integer> A) {
            int l = 0;
            int r = A.size() - 1;
            int lm = 0;
            int rm = 0;
            int result = 0;
            while (l <= r) {
                if (A.get(l) < A.get(r)) {
                    if   (lm < A.get(l)) lm = A.get(l);
                    else result += lm - A.get(l++);
                } else {
                    if (rm < A.get(r)) rm = A.get(r);
                    else result += rm - A.get(r--);
                }
            }
            return result;
        }
    }

}
