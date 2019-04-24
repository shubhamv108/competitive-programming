package code.hashing;

import java.util.*;

public class TwoSum {
    class Solution {
        public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
            ArrayList<Integer> result   = new ArrayList<>();
            Map<Integer, Integer> m = new HashMap<>();
            for (int i = 0; i < A.size(); i++) {
                if (m.containsKey(B - A.get(i))) {
                    result.add(m.get(B - A.get(i)) + 1);
                    result.add(i + 1);
                    break;
                } else {
                    m.putIfAbsent(A.get(i), i);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new TwoSum().
                new Solution().
                twoSum(new ArrayList<>(Arrays.asList(2, 2,  7, 11, 15)), 9).
                forEach(System.out::print);
    }

}
