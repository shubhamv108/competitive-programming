package code.shubham.arrays;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public class Solution {
        public List<String> findMissingRanges(int[] A, int lower, int upper) {
            ArrayList<String> result = new ArrayList<>();
            if (A == null || A.length == 0) {
                addRange(result, lower, upper);
                return result;
            }

            addRange(result, lower, (long) A[0] - 1);

            for (int i = 1; i < A.length; ++i)
                addRange(result, (long) A[i - 1] + 1, (long) A[i] - 1);

            addRange(result, (long) A[A.length - 1] + 1, upper);

            return result;
        }

        void addRange(List<String> result, long l, long r) {
            if (l > r)
                return;

            if (l == r) {
                result.add("" + l);
                return;
            }

            result.add(l + "->" + r);
        }
    }
}
