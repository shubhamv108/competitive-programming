package code.binarysearch;

import java.util.HashMap;
import java.util.Map;

public class FrequencyOfAllNumbersInSortedArray {


    class Solution {
        Map<Integer, Integer> m = new HashMap<>();

        Map<Integer, Integer> freq(int[] A) {
            recurse(A, 0, A.length - 1);
            return m;
        }

        void recurse(int[] A, int l, int r) {
            if (A[l] == A[r])
                m.put(A[l], m.getOrDefault(A[l], 0) + (r - l + 1));
            else {
                int m = l + (r - l) / 2;
                recurse(A, l, m);
                recurse(A, m+1, r);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new FrequencyOfAllNumbersInSortedArray().new Solution()
                .freq(new int[] { 1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10 }));
    }

}
