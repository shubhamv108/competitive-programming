package code.contestpractice.oa.microsoft;

import java.util.HashSet;

public class LargestKSuchThatBothKAndMinusKExistInArray {

    class Solution {
        int solve(int[] A) {
            int result = Integer.MIN_VALUE;
            HashSet<Integer> set = new HashSet<>();
            for (int a : A) {
                if (set.contains(-a))
                    result = Math.max(result, Math.abs(a));
                set.add(a);
            }
            return result;
        }
    }

}
