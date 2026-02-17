package code.shubham.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    class Solution {
        public List<List<Integer>> subsets(int[] A) {
            ArrayList<List<Integer>> result = new ArrayList<>();
            f(A, 0, new ArrayList<>(), result);
            return result;
        }

        void f(int[] A, int index, ArrayList<Integer> l, ArrayList<List<Integer>> result) {
            result.add(new ArrayList<>(l));

            for (int i = index; i < A.length; i++) {
                l.add(A[i]);
                f(A, i + 1, l, result);
                l.remove(l.size() - 1);
            }
        }
    }

    class Solution2 {
        public List<List<Integer>> subsets(int[] A) {
            int n = A.length;
            int total = 1 << n;
            ArrayList<List<Integer>> result = new ArrayList<>(total);
            for (int i = 0; i < total; ++i) {
                List<Integer> l =  new ArrayList<>();
                for (int j = 0; j < n; ++j)
                    if ((i & (1 << j)) != 0)
                        l.add(A[j]);
                result.add(l);
            }
            return result;
        }
    }

}
