package code.arrays;

import java.util.Arrays;

public class MinStepsToMakePilesEqualHeight {

    int steps(int[] piles) {
        int result = 0;
        Integer[] sorted = Arrays.stream(piles).boxed().sorted((a, b) -> b-a).toArray(Integer[]::new);
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i - 1] != sorted[i]) {
                result += i;
            }
        }
        return result;
    }

}
