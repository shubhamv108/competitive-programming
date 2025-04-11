package code.shubham.binarysearch;

import java.util.Arrays;

public class CuttingRibbons {

    class Solution {
        public int maxLength(int[] A, int k) {
            int l = 0, r = Arrays.stream(A).max().getAsInt();
            while (l < r) {
                int m = l + ((r - l + 1) >> 1);
                if (count(A, m, k))
                    l = m;
                else
                    r = m - 1;
            }

            return l;
        }

        boolean count(int[] A, int m, int k) {
            int c = 0;
            for (int a : A) {
                c += a / m;
                if (c >= k)
                    return true;
            }

            return false;
        }
    }

}
