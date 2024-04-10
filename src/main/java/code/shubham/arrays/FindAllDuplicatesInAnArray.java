package code.shubham.arrays;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class FindAllDuplicatesInAnArray {
    class Solution {
        public List<Integer> findDuplicates(int[] A) {
            ArrayList<Integer> result = new ArrayList<>();
            boolean[] f = new boolean[A.length + 1];
            for (int i = 0; i < A.length; ++i) {
                if (f[A[i]])
                    result.add(A[i]);
                else
                    f[A[i]] = true;
            }
            return result;
        }
    }

    class Solution2 {
        public List<Integer> findDuplicates(int[] A) {
            ArrayList<Integer> result = new ArrayList<>();
            int ai;
            for (int i = 0; i < A.length; ++i) {
                ai = Math.abs(A[i]);
                if (A[ai - 1] < 0)
                    result.add(ai);
                else
                    A[ai - 1] *= -1;
            }
            return result;
        }
    }
}
