package code.shubham.topk;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    class Solution {
        public List<Integer> findClosestElements(int[] A, int k, int x) {
            ArrayList<Integer> result = new ArrayList<>();

            int l = 0, r = A.length - k;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (x - A[m] > A[m + k] - x)
                    l = m + 1;
                else
                    r = m;
            }

            int end = l + k;
            for (int i = l; i < end; ++i)
                result.add(A[i]);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindKClosestElements().new Solution().findClosestElements(new int[] { 1,1,2,3,4,5 }, 4, -1));
    }
}
