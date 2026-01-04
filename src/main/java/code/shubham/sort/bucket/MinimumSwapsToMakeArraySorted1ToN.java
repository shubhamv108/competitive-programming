package code.shubham.sort.bucket;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumSwapsToMakeArraySorted1ToN {

    class Solution {
        static int minSwaps(int[] A) {
            int result = 0;

            Map<Integer, Integer> pos = IntStream.range(0, A.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> A[i], Function.identity()));

            Arrays.sort(A);

            boolean[] v = new boolean[A.length];

            for (int i = 0; i < A.length; ++i) {
                if (v[i] || pos.get(A[i]) == i)
                    continue;

                int j = i, cycleSize = 0;
                while (!v[j]) {
                    v[j] = true;
                    j = pos.get(A[j]);
                    cycleSize++;
                }

                if (cycleSize > 0)
                    result += (cycleSize - 1);
            }
            return result;
        }

        public static void main(String[] __) {
            int[] arr = {10, 19, 6, 3, 5};
            System.out.println(minSwaps(arr));
        }
    }
}
