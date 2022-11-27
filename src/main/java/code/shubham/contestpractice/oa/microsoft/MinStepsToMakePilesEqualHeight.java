package code.shubham.contestpractice.oa.microsoft;

import java.util.Arrays;

public class MinStepsToMakePilesEqualHeight {

    class Solution {
        int solve(int[] piles) {
            int result = 0;
            Integer[] sorted = Arrays.stream(piles).
                    boxed().
                    sorted((a, b) -> b-a).
                    toArray(Integer[]::new);
            for (int i = 1; i < sorted.length; i++)
                if (sorted[i - 1] != sorted[i])
                    result += i;
            return result;
        }
    }

}
