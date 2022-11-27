package code.shubham.dynamicprogramming;

import java.util.ArrayList;

public class MaxSumWithoutAdjacentElements2IntoNGrid {
    public class Solution {
        public int adjacent(ArrayList<ArrayList<Integer>> A) {
            int incl = Math.max(A.get(0).get(0), A.get(1).get(0));
            int excl = 0, excl_new;
            for (int i = 1; i < A.get(0).size(); i++ ) {
                excl_new = Math.max(excl, incl);
                incl = excl + Math.max(A.get(0).get(i), A.get(1).get(i));
                excl = excl_new;
            }
            return Math.max(excl, incl);
        }
    }
}
