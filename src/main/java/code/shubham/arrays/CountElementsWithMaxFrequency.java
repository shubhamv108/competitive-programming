package code.shubham.arrays;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountElementsWithMaxFrequency {
    class Solution {
        public int maxFrequencyElements(int[] A) {
            var freq = Arrays.stream(A)
                    .mapToObj(e -> e)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .values();

            long max = freq
                    .stream()
                    .mapToLong(e -> e)
                    .max()
                    .orElse(A[0]);

            return (int) freq
                    .stream()
                    .filter(e -> e == max)
                    .count();
        }
    }
}
