package code.shubham.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SubArrayContainingAllElementsInArray {

    class Solution {
        int solve(int[] A) {
            int len = A.length;
            HashSet<Integer> set = new HashSet<>();
            for (int n : A) set.add(n);
            int uniqueCount = set.size();

            Map<Integer, Integer> m = new HashMap<>();
            int start = 0, i = 0;
            for (; i < len && m.size() < uniqueCount; i++) {
                m.put(A[i], m.getOrDefault(A[i], 0) + 1);
                while (m.get(A[start]) > 1) {
                    m.put(A[start], m.get(A[start]) - 1);
                    start++;
                }
            }
            int result = i - start + 1;
            for (; i < len; i++) {
                m.put(A[i], m.getOrDefault(A[i], 0) + 1);
                while (m.get(A[start]) > 1) {
                    m.put(A[start], m.get(A[start]) - 1);
                    start++;
                }
                result = Math.min(result, i - start + 1);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SubArrayContainingAllElementsInArray subArrayContainingAllElementsInArray = new SubArrayContainingAllElementsInArray();
        int r = subArrayContainingAllElementsInArray.new Solution().solve(new int[] { 1, 1, 2, 2, 3, 2, 1, 3, 1 });
        System.out.println(r);
    }

}
