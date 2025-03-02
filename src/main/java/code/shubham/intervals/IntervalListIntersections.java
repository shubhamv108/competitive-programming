package code.shubham.intervals;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 */
public class IntervalListIntersections {

    class Solution {
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            ArrayList<int[]> result = new ArrayList();

            for (int ai = 0, bi = 0; ai < A.length && bi < B.length; ) {
                int start = Math.max(A[ai][0], B[bi][0]);
                int end   = Math.min(A[ai][1], B[bi][1]);

                if (start <= end)
                    result.add(new int[] { start, end });

                if (A[ai][1] < B[bi][1])
                    ++ai;
                else
                    ++bi;
            }

            return result.toArray(int[][]::new);
        }
    }

}
