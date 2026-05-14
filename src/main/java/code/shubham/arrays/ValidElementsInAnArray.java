package code.shubham.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ValidElementsInAnArray {

    class Solution {
        public List<Integer> findValidElements(int[] A) {
            int n = A.length;
            ArrayList<Integer> result = new ArrayList<>();
            result.add(A[0]);

            int i = 1;
            for (i = 1; i < n; ++i) {
                if (A[i] <= A[i-1])
                    break;
                result.add(A[i]);
            }

            if (i == n)
                return result;


            LinkedList<Integer> rev = new LinkedList<>();
            rev.add(A[n-1]);
            for (int j = n - 2; j >= i; --j) {
                if (A[j] <= A[j+1])
                    break;
                rev.offerFirst(A[j]);
            }

            result.addAll(rev);
            return result;
        }
    }

    void main() {
        System.out.println(new ValidElementsInAnArray().new Solution().findValidElements(new int[] { 4,1,7,7 }));
    }

}
