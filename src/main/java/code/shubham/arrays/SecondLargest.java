package code.shubham.arrays;

import java.util.Optional;
import java.util.OptionalInt;

public class SecondLargest {

    public Optional solve(int[] A) {
        if (A == null && A.length < 2)
            return Optional.empty();

        int n = A.length;
        Integer l = null, sl = null;
        for (int i = 2; i < n; ++i) {
            int a = A[i];
            if (l == null || a > l) {
                sl = l;
                l = a;
            } else if (a < l && (sl == null || a > sl)) {
                sl = a;
            }
        }
        return Optional.ofNullable(sl);
    }
}
