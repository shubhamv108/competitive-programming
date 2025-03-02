package code.shubham.intervals;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertIntervals {

    class Solution {
        public int[][] insert(int[][] A, int[] n) {
            ArrayList<int[]> result = new ArrayList<>(A.length + 1);

            // add all before n range
            int i = 0;
            for (; i < A.length && A[i][1] < n[0]; ++i)
                result.add(A[i]);

            // combine all in n range in to n
            for (; i < A.length && n[1] >= A[i][0]; ++i) {
                n[0] = Math.min(n[0], A[i][0]);
                n[1] = Math.max(n[1], A[i][1]);
            }
            result.add(n);

            // add all after n range
            for (; i < A.length; ++i)
                result.add(A[i]);

            return result.toArray(int[][]::new);
        }
    }

}
