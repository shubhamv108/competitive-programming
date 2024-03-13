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

    class Solution2 {
        int solve(int[] A) {
            int result = 0;
            Arrays.sort(A);
            for (int i = A.length - 1; i > -1; --i)
                if (A[i] != A[i + 1])
                    result += i;
            return result;
        }
    }

}
