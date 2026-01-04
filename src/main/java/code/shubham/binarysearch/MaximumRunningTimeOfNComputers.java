package code.shubham.binarysearch;

import java.util.Arrays;

public class MaximumRunningTimeOfNComputers  {

    class Solution {
        public long maxRunTime(int n, int[] A) {
            long sum = Arrays.stream(A).sum();

            long l = 0, r = sum/n;
            while (l < r) {
                long m = l + (r - l) / 2;

                long t = Arrays.stream(A)
                        .mapToLong(a -> Math.min(a, m))
                        .sum();

                if (t >= n * m)
                    l = m;
                else
                    r = m - 1;
            }
            return l;
        }
    }

}
